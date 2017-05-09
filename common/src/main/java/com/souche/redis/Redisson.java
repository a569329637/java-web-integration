package com.souche.redis;

import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;

/**
 * @author guishangquan
 * @Description 发布时会有问题，连接不上服务器或者是不支持 eval 命令
 * @Package com.souche.redis
 * @date 17/4/27
 **/
public class Redisson {

    private RedissonClient redissonClient;


    public void init() {
        String address = "127.0.0.1:6379";
        String database = "0";
        String password = "";

        Config config = new Config();
        config.useSingleServer()
                .setAddress(address)
                .setDatabase(Integer.parseInt(database))
                .setPassword(password);
        config.setCodec(StringCodec.INSTANCE);

        redissonClient = org.redisson.Redisson.create(config);
    }

    public void destroy() {
        if (redissonClient != null) {
            redissonClient.shutdown();
        }
    }
}
