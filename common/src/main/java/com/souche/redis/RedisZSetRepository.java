package com.souche.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Set;

@Repository("redisZSetRepository")
public class RedisZSetRepository {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void add(String key, String value, double score) {
        if (StringUtils.isEmpty(key)) {
            return;
        }
        redisTemplate.opsForZSet().add(key, value, score);
    }

    public Set<String> rangeByScore(String key, double score, double score1) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        return redisTemplate.opsForZSet().rangeByScore(key, score, score1);
    }

    public void incrementScore(String key, String value, double score) {
        if (StringUtils.isEmpty(key)) {
            return;
        }
        redisTemplate.opsForZSet().incrementScore(key, value, score);
    }


    public void delete(String key) {
        if (StringUtils.isEmpty(key)) {
            return;
        }
        redisTemplate.delete(key);
    }

}
