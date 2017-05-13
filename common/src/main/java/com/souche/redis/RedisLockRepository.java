package com.souche.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository("redisLockRepository")
public class RedisLockRepository {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    public boolean lock(final String key, final int second) {
        if (getLock(key)) {
            return false;
        }
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] keyByte = serializer.serialize(key);
                byte[] valueBtye = serializer.serialize((System.currentTimeMillis() + second * 1000) + ""); // 值为超时时间点，毫秒
                boolean result = connection.setNX(keyByte, valueBtye);
                if (result) {
                    return connection.expire(keyByte, second); // 如果超时时间设置失败，也返回失败
                }
                return result;
            }
        });
        return result;
    }


    public void releaseLock(final String key) {
        if (StringUtils.isEmpty(key)) {
            return;
        }
        redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] keyByte = serializer.serialize(key);
                return connection.del(keyByte);
            }
        });
    }


    public boolean getLock(final String key) {
        if (StringUtils.isEmpty(key)) {
            return true;
        }
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] keyByte = serializer.serialize(key);
                String value = serializer.deserialize(connection.get(keyByte));
                if (StringUtils.isEmpty(value)) {
                    return false;
                }
                if (System.currentTimeMillis() > Long.parseLong(value)) {
                    connection.del(keyByte);
                    return false;
                }
                return true;
            }
        });
        return result;
    }

}
