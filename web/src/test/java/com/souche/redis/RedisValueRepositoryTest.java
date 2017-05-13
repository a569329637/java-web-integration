package com.souche.redis;

import com.alibaba.fastjson.JSONObject;
import com.souche.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.coupon.redis
 * @date 17/5/5
 **/
public class RedisValueRepositoryTest extends BaseTest {

    @Autowired
    private RedisValueRepository redisValueRepository;

    @Test
    public void testAllMethod() {
        String key = "user_info";
        redisValueRepository.delete(key);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", "1039273");
        jsonObject.put("phone", "1353764885");
        String jsonString = jsonObject.toJSONString();
        redisValueRepository.set(key, jsonString, 30);

        String redisString = redisValueRepository.get(key);
        JSONObject object = JSONObject.parseObject(redisString);
        System.out.println("object = " + object);
    }

}
