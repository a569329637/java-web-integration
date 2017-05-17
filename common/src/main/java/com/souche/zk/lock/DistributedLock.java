package com.souche.zk.lock;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.apache.zookeeper.CreateMode.EPHEMERAL_SEQUENTIAL;
import static org.apache.zookeeper.CreateMode.PERSISTENT;
import static org.apache.zookeeper.ZooDefs.Ids.OPEN_ACL_UNSAFE;

/**
 * @author guishangquan
 * @Description 分布式锁
 * @Package com.souche.zk.lock
 * @date 17/5/17
 **/
public class DistributedLock implements Watcher {
    private ZooKeeper zooKeeper;
    // 根节点
    private String root = "/locks";
    // 竞争资源的标志
    private String lockName;
    // 等待前一个锁
    private String waitName;
    // 当前锁
    private String myZnode;
    // 计数器
    private CountDownLatch latch;
    // 超时时间
    private int sessionTimeout = 30000;
    private List<Exception> exceptions = new ArrayList<Exception>();

    public DistributedLock(String host, String lockName) {
        this.lockName = lockName;
        try {
            zooKeeper = new ZooKeeper(host, sessionTimeout, this);
            Stat stat = zooKeeper.exists(root, false);
            if (stat == null) {
                zooKeeper.create(root, new byte[0], OPEN_ACL_UNSAFE, PERSISTENT);
            }
            System.out.println("线程" + Thread.currentThread().getId() + "开始");
        } catch (IOException e) {
            exceptions.add(e);
        } catch (InterruptedException e) {
            exceptions.add(e);
        } catch (KeeperException e) {
            exceptions.add(e);
        }
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        // 创建 zooKeeper 时不会进入
        if (this.latch != null) {
            this.latch.countDown();
        }
    }

    public boolean lock() {
        if (exceptions.size() > 0) {
            throw new LockException(exceptions.get(0));
        }
        try {
            if (tryLock()) {
                System.out.println("线程" + Thread.currentThread().getId() + ",获得锁" + myZnode);
                return true;
            } else {
                System.out.println("线程" + Thread.currentThread().getId() + ",锁" + myZnode + ",等待" + waitName);
                return waitForLock(waitName, sessionTimeout);
            }
        } catch (KeeperException e) {
            throw new LockException(e);
        } catch (InterruptedException e) {
            throw new LockException(e);
        }
    }

    private boolean tryLock() {
        try {
            String splitStr = "_lock_";
            if (this.lockName.contains(splitStr)) {
                throw new LockException("竞争资源的名称不能包含 \'_lock_\'");
            }
            // 创建节点
            myZnode = zooKeeper.create(root + "/" + lockName + splitStr,
                    new byte[0], OPEN_ACL_UNSAFE, EPHEMERAL_SEQUENTIAL);

            // 取出所有子节点
            List<String> children = zooKeeper.getChildren(root, false);
            // 取出所有 lockName 的锁
            List<String> lockNameNodes = new ArrayList<>();
            for (String child : children) {
                String _child = child.split(splitStr)[0];
                if (_child.equals(lockName)) {
                    lockNameNodes.add(child);
                }
            }
            Collections.sort(lockNameNodes);

            if (myZnode.equals(root + "/" + lockNameNodes.get(0))) {
                // 如果是最小的节点,则表示取得锁
                return true;
            } else {
                // 如果不是最小的节点，找到比自己小的节点
                String subMyZnode = myZnode.substring(myZnode.lastIndexOf("/") + 1);
                waitName = lockNameNodes.get(Collections.binarySearch(lockNameNodes, subMyZnode) - 1);
            }
        } catch (KeeperException e) {
            throw new LockException(e);
        } catch (InterruptedException e) {
            throw new LockException(e);
        }
        return false;
    }

    private boolean waitForLock(String lower, long waitTime) throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.exists(root + "/" + lower,true);
        if (stat != null) {
            this.latch = new CountDownLatch(1);
            // 超时的话就没有拿到，返回 false
            boolean await = this.latch.await(waitTime, TimeUnit.MILLISECONDS);
            System.out.println("线程" + Thread.currentThread().getId() + ",锁" + myZnode + ",等待" + waitName + ",等待结果" + await);
            this.latch = null;
            // 这里返回 false 的时候，可以考虑删除当前节点
            return await;
        }
        System.out.println("线程" + Thread.currentThread().getId() + ",锁" + myZnode + ",等待" + waitName + ",等待结果" + true);
        return true;
    }

    public void unlock() {
        try {
            System.out.println("线程" + Thread.currentThread().getId() + ",释放锁" + myZnode);
            zooKeeper.delete(myZnode, -1);
            myZnode = null;
            zooKeeper.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    public class LockException extends RuntimeException {
        private static final long serialVersionUID = 1L;
        public LockException(String e){
            super(e);
        }
        public LockException(Exception e){
            super(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; ++ i) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    DistributedLock lock = new DistributedLock("127.0.0.1", "test");
                    lock.lock();
                    try {
                        // 延时 4 秒的话会有两个拿不到锁
                        sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.unlock();
                }
            }).start();
        }
        sleep(Long.MAX_VALUE);
    }
}
