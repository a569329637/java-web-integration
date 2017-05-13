package com.souche.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;


@Repository("redisListRepository")
public class RedisListRepository {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void rpush(String key, String value) {
        if (StringUtils.isEmpty(key)) {
            return;
        }
        redisTemplate.opsForList().rightPush(key, value);
    }


    public void rpushAll(String key, List<String> values) {
        if (StringUtils.isEmpty(key)) {
            return;
        }
        for (String value : values) {
            rpush(key, value);
        }
    }


    public String lpop(String key) {
        if (StringUtils.isEmpty(key)) {
            return "";
        }
        return redisTemplate.opsForList().leftPop(key);
    }


    public List<String> range(String key, int start, int end) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        return redisTemplate.opsForList().range(key, start, end);
    }


    public Long length(String key) {
        if (StringUtils.isEmpty(key)) {
            return 0L;
        }
        return redisTemplate.opsForList().size(key);
    }


    public void del(String key) {
        if (StringUtils.isEmpty(key)) {
            return;
        }
        redisTemplate.delete(key);
    }
}
