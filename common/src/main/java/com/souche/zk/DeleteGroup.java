package com.souche.zk;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.List;

/**
 * @author guishangquan
 * @Description 删除 zk 组
 * @Package com.souche.zk
 * @date 17/5/17
 **/
public class DeleteGroup extends ConnectionWatcher {
    public void deleteGroup(String groupName) {
        String path = "/" + groupName;
        List<String> children;
        try {
            children = zooKeeper.getChildren(path, false);
            for (String child : children) {
                zooKeeper.delete(child, -1);
            }
            zooKeeper.delete(path, -1);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        DeleteGroup deleteGroup = new DeleteGroup();
        deleteGroup.connect("127.0.0.1");
        deleteGroup.deleteGroup("zk");
        deleteGroup.close();
    }
}
