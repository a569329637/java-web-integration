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
public class TestAdvice extends BaseTest {

    @Autowired
    private Waiter greetBeforeWaiter;

    @Autowired
    private Waiter greetAfterWaiter;

    @Autowired
    private NaiveWaiter greetingInterceptorWaiter; // 可以使 Waiter 或 NaiveWaiter

    @Autowired
    private NaiveWaiter greetThrowWaiter;

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

    @Test
    public void testGreetThrowAdvice() {
        // #4
        try {
            greetThrowWaiter.exceptionTest();
        } catch (Exception e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }
    }

}
