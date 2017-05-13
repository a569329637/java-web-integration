package com.souche.redis;

import com.souche.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RedisLockRepositoryTest extends BaseTest {
    @Autowired
    private RedisLockRepository redisLockRepository;

    @Test
    public void testLock() {
        redisLockRepository.lock("try_lock", 30);

//        try {
//            sleep(60000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        redisLockRepository.releaseLock("try_lock");
    }
}
