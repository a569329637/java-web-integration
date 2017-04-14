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

    @Autowired
    private Waiter greetAfterWaiter;

    @Autowired
    private NaiveWaiter greetingInterceptorWaiter; // 可以使 Waiter 或 NaiveWaiter

    @Test
    public void testGreetBeforeAdvice() {
        // #1
        greetBeforeWaiter.greetTo("name1");
        greetBeforeWaiter.serveTo("name2");
    }

    @Test
    public void testGreetAfterAdvice() {
        // #2
        greetAfterWaiter.greetTo("name1");
        greetAfterWaiter.serveTo("name2");
    }

    @Test
    public void testGreetingInterceptor() {
        // #3
        greetingInterceptorWaiter.greetTo("name1");
        greetingInterceptorWaiter.serveTo("name2");
    }

}
