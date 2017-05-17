package com.souche.zk;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;

import java.io.IOException;

/**
 * @author guishangquan
 * @Description 创建 zk 组节点
 * @Package com.souche.zk
 * @date 17/5/17
 **/
public class CreateGroup extends ConnectionWatcher {
    public void createGroup(String groupName) throws KeeperException, InterruptedException {
        String path = "/" + groupName;
        String createName = zooKeeper.create(path, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("createName = " + createName);
    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        CreateGroup createGroup = new CreateGroup();
        createGroup.connect("localhost");
        createGroup.createGroup("zk");
        createGroup.close();
    }
}
