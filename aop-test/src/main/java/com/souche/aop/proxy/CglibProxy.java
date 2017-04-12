package com.souche.aop.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.aop.proxy
 * @date 17/4/12
 **/
public class CglibProxy implements MethodInterceptor {
    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class clazz) {
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    public Object intercept(Object target, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        PerformanceMonitor.begin(target.getClass().getName() + "." + method.getName());
        Object obj = methodProxy.invokeSuper(target, args);
        PerformanceMonitor.end();
        return obj;
    }
}
