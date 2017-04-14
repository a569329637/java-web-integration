package com.souche.aop.advice;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.aop.advice
 * @date 17/4/14
 **/
public class GreetAfterAdvice implements AfterReturningAdvice {

    public void afterReturning(Object o, Method method, Object[] args, Object o1) throws Throwable {
        System.out.println("please enjoy yourself!");
    }
}

class TestGreetAfterAdvice {

    public static void main(String[] args) {
        Waiter target = new NaiveWaiter();
        GreetAfterAdvice greetAfterAdvice = new GreetAfterAdvice();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvice(greetAfterAdvice);
        Waiter proxy = (Waiter) proxyFactory.getProxy();

        proxy.greetTo("name1");
        proxy.serveTo("name2");
    }
}
