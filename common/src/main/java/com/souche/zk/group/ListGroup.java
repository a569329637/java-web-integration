package com.souche.zk.group;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.List;

/**
 * @author guishangquan
 * @Description 获取 zk 列表
 * @Package com.souche.zk
 * @date 17/5/17
 **/
public class ListGroup extends ConnectionWatcher {
    public void list(String groupName) {
        String path = "/" + groupName;
        try {

            List<String> children = zooKeeper.getChildren(path, false);
            if (children.isEmpty()) {
                System.out.printf("No memebers in group %s\n", groupName);
                return;
            }
            for (String child : children) {
                System.out.println("child = " + child);
            }

        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ListGroup listGroup = new ListGroup();
        listGroup.connect("127.0.0.1");
        listGroup.list("");
        listGroup.close();
    }
}
