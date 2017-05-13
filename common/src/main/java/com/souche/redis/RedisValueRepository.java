package com.souche.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Repository("redisValueRepository")
public class RedisValueRepository {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    public void set(String key, String value) {
        if (StringUtils.isEmpty(key)) {
            return;
        }
        redisTemplate.opsForValue().set(key, value);
    }


    public void set(String key, String value, int second) {
        if (StringUtils.isEmpty(key)) {
            return;
        }
        redisTemplate.opsForValue().set(key, value, second, TimeUnit.SECONDS);
    }


    public void delete(String key) {
        if (StringUtils.isEmpty(key)) {
            return;
        }
        redisTemplate.delete(key);
    }


    public String get(String key) {
        if (StringUtils.isEmpty(key)) {
            return "";
        }
        return (String) redisTemplate.opsForValue().get(key);
    }
}
