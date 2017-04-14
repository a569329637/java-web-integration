package com.souche.aop.advisor;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.aop.advisor
 * @date 17/4/14
 **/
public class GreetingBeforeAdvice implements MethodBeforeAdvice {

    public void before(Method method, Object[] args, Object o) throws Throwable {
        System.out.println(o.getClass().getName() + "." + method.getName());
        String name = (String) args[0];
        System.out.println("how are you! Mr." + name);
    }
}
