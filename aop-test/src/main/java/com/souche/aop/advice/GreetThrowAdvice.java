package com.souche.aop.advice;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.aop.advice
 * @date 17/4/14
 **/
public class GreetThrowAdvice implements ThrowsAdvice {

    // 没有定义任何方法可以实现，它是一个标识接口
    public void afterThrowing(Method method, Object[] args, Object target, Exception ex) throws Throwable {
        System.out.println("-------");
        System.out.println("method = " + method.getName());
        System.out.println("抛出了异常 = " + ex.getMessage());
        System.out.println("事务成功回滚！");
    }

}

class TestGreetThrowAdvice {

    public static void main(String[] args) {
        Waiter target = new NaiveWaiter();
        GreetThrowAdvice greetThrowAdvice = new GreetThrowAdvice();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvice(greetThrowAdvice);
        Waiter proxy = (Waiter) proxyFactory.getProxy();

        proxy.exceptionTest();
    }
}
