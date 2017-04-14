package com.souche.aop.advice;

import com.souche.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.aop.advice
 * @date 17/4/14
 **/
public class TestBeforeAdvice extends BaseTest {

    @Autowired
    private Waiter greetBeforeWaiter;

    @Test
    public void testGreetBeforeAdvice() {
        // #1
        greetBeforeWaiter.greetTo("name1");
        greetBeforeWaiter.serveTo("name2");
    }

}
