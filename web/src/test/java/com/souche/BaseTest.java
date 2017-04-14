package com.souche;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche
 * @date 17/4/14
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/spring.xml") // Spring配置文件的入口
@Transactional // 确认有 transactionManager 这个 bean
@Rollback // 默认回滚
public class BaseTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Test
    public void test() {}

}
