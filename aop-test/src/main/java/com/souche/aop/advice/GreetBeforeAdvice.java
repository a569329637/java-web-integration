package com.souche.aop.advice;

import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.aop.advice
 * @date 17/4/14
 **/
public class GreetBeforeAdvice implements MethodBeforeAdvice {

    public void before(Method method, Object[] args, Object o) throws Throwable {
        String name = (String) args[0];
        System.out.println("how are you! Mr." + name);
    }
}


class TestBeforeAdvice {

    public static void main(String[] args) {
        // 代理目标
        Waiter target = new NaiveWaiter();
        // 增强
        BeforeAdvice advice = new GreetBeforeAdvice();
        // 代理工厂
        ProxyFactory proxyFactory = new ProxyFactory();
        // 对接口进行代理
//        proxyFactory.setInterfaces(target.getClass().getInterfaces());
        // 启动优化，使用 cglib 生成代理对象
//        proxyFactory.setOptimize(true);
        // 设置代理目标
        proxyFactory.setTarget(target);
        // 设置代理增强
        proxyFactory.addAdvice(advice);
        // 生成代理实例
        Waiter proxy = (Waiter) proxyFactory.getProxy();

        proxy.greetTo("name1");
        proxy.serveTo("name2");
    }
}
