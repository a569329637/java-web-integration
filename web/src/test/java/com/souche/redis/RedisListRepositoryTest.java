package com.souche.redis;

import com.souche.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class RedisListRepositoryTest extends BaseTest {

    @Autowired
    private RedisListRepository redisListRepository;

    @Test
    public void testAllMethod() {
        String key = "coupon_batch:0:coupon_list:test";

        redisListRepository.del(key);

        String value = "test_value";
        redisListRepository.rpush(key, value);
        String popValue = redisListRepository.lpop(key);
        Assert.assertEquals(popValue, value);

        List<String> list = new ArrayList<>();
        list.add("test_value1");
        list.add("test_value2");
        list.add("test_value3");
        redisListRepository.rpushAll(key, list);
        Long len = redisListRepository.length(key);
        Assert.assertEquals(len.intValue(), list.size());
        List<String> range = redisListRepository.range(key, 0, -1);
        Assert.assertTrue(range.equals(list));

        redisListRepository.del(key);
        Long length = redisListRepository.length(key);
        Assert.assertEquals(length.intValue(), 0);
    }
}
