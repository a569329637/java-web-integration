package com.souche;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche
 * @date 17/4/14
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/spring.xml") // Spring配置文件的入口
public abstract class BaseTest extends AbstractTransactionalJUnit4SpringContextTests {
}
