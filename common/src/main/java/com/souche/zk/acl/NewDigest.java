package com.souche.zk.acl;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.zk.acl
 * @date 17/5/18
 **/
public class NewDigest {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        List<ACL> acls = new ArrayList<>();
        Id id1 = new Id("digest", DigestAuthenticationProvider.generateDigest("admin:123456"));
        ACL acl1 = new ACL(ZooDefs.Perms.ALL, id1);
        acls.add(acl1);
        Id id2 = new Id("world", "anyone");
        ACL acl2 = new ACL(ZooDefs.Perms.READ, id2);
        acls.add(acl2);

        try {
            ZooKeeper zk = new ZooKeeper("localhost", 2000, null);
            zk.addAuthInfo("digest", "admin:123456".getBytes());
            zk.create("/auth_test", "data".getBytes(), acls, CreateMode.PERSISTENT);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }
}
