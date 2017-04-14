package com.souche.aop.advice;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;

/**
 * @author guishangquan
 * @Description 环绕增强
 * @Package com.souche.aop.advice
 * @date 17/4/14
 **/
public class GreetingInterceptor implements MethodInterceptor {

    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        // 获取方法参数
        Object[] arguments = methodInvocation.getArguments();
        String name = (String) arguments[0];

        System.out.println("how are you! Mr." + name);

        // 通过反射调用目标方法
        Object ret = methodInvocation.proceed();

        System.out.println("please enjoy yourself!");
        return ret;
    }
}

class TestGreetingInterceptor {

    public static void main(String[] args) {
        Waiter target = new NaiveWaiter();
        GreetingInterceptor greetingInterceptor = new GreetingInterceptor();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvice(greetingInterceptor);
        Waiter proxy = (Waiter) proxyFactory.getProxy();

        proxy.greetTo("name1");
        proxy.serveTo("name2");
    }
}
