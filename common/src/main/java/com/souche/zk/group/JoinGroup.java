package com.souche.zk.group;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;

import java.io.IOException;

/**
 * @author guishangquan
 * @Description 添加 zk 节点
 * @Package com.souche.zk
 * @date 17/5/17
 **/
public class JoinGroup extends ConnectionWatcher {
    public void join(String groupName, String memberName) throws KeeperException, InterruptedException {
        String path = "/" + groupName + "/" + memberName;
        String createName = zooKeeper.create(path, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("createName = " + createName);
    }

    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {
        JoinGroup joinGroup = new JoinGroup();
        joinGroup.connect("127.0.0.1");
        joinGroup.join("zk", "zk1");
        Thread.sleep(Long.MAX_VALUE);
//        joinGroup.close();
    }
}
