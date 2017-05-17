package com.souche.zk.config;

import com.souche.zk.ConnectionWatcher;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;

import java.nio.charset.Charset;

/**
 * @author guishangquan
 * @Description 写配置，
 * @Package com.souche.zk.config
 * @date 17/5/17
 **/
public class ActiveKeyValueStore extends ConnectionWatcher {
    private static final Charset CHARSET=Charset.forName("UTF-8");

    public void write(String path,String value) throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.exists(path, false);
        if(stat==null){
            zooKeeper.create(path, value.getBytes(CHARSET), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }else{
            zooKeeper.setData(path, value.getBytes(CHARSET),-1);
        }
    }

    public String read(String path,Watcher watch) throws KeeperException, InterruptedException{
        byte[] data = zooKeeper.getData(path, watch, null);
        return new String(data,CHARSET);

    }
}
