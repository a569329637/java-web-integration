package com.souche.zk.config;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.io.IOException;

/**
 * @author guishangquan
 * @Description 配置监视器
 * @Package com.souche.zk.config
 * @date 17/5/17
 **/
public class ConfigWatcher implements Watcher {
    private ActiveKeyValueStore store;

    @Override
    public void process(WatchedEvent event) {
        if(event.getType()== Event.EventType.NodeDataChanged){
            try{
                displayConfig();
            }catch(InterruptedException e){
                System.err.println("Interrupted. exiting. ");
                Thread.currentThread().interrupt();
            }catch(KeeperException e){
                System.out.printf("KeeperException锛?s. Exiting.\n", e);
            }

        }

    }
    public ConfigWatcher(String hosts) throws IOException, InterruptedException {
        store=new ActiveKeyValueStore();
        store.connect(hosts);
    }
    public void displayConfig() throws KeeperException, InterruptedException{
        String value=store.read(ConfigUpdater.PATH, this);
        System.out.printf("Read %s as %s\n",ConfigUpdater.PATH,value);
    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ConfigWatcher configWatcher = new ConfigWatcher("localhost");
        configWatcher.displayConfig();
        //stay alive until process is killed or Thread is interrupted
        Thread.sleep(Long.MAX_VALUE);
    }
}
