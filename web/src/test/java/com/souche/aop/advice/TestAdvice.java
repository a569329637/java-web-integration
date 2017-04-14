package com.souche.aop.advice;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.souche.BaseTest;
import com.souche.aop.service.ForumService;

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

    @Autowired
    private Monitorable monitor;

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

    @Test
    public void testIntroceAdvice() {
        // #5
        ForumService forumService = (ForumService) monitor;

        forumService.removeForum(1);
        forumService.removeTopic(2);

        monitor.setMonitorActive(true);

        forumService.removeForum(1);
        forumService.removeTopic(2);

    }

}
