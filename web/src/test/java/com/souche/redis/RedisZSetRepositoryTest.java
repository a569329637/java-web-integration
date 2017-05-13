package com.souche.redis;

import com.souche.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.coupon.redis
 * @date 17/3/21
 **/
public class RedisZSetRepositoryTest extends BaseTest {

    @Autowired
    private RedisZSetRepository redisZSetRepository;

    @Test
    public void testAllMethod() {
        String key = "coupon_batch:0:pool_req_no";

        redisZSetRepository.delete(key);

        for (int i = 0; i < 5; ++ i) {
            String value = "value_" + i;
            double score = i;
            redisZSetRepository.add(key, value, score);
        }

        Set<String> valueSet = redisZSetRepository.rangeByScore(key, 1, 3);
        for (String value : valueSet) {
            System.out.println("value = " + value);
        }

        redisZSetRepository.incrementScore(key, "value_0", 2);
        valueSet = redisZSetRepository.rangeByScore(key, 1, 3);
        for (String value : valueSet) {
            System.out.println("value = " + value);
        }

        redisZSetRepository.delete(key);
    }

}
