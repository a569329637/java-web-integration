package com.souche.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.aop.proxy
 * @date 17/4/12
 **/
public class PerformanceHandler implements InvocationHandler {
    private Object target;

    public PerformanceHandler(Object target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        PerformanceMonitor.begin(target.getClass().getName() + "." + method.getName());
        Object obj = method.invoke(target, args);
        PerformanceMonitor.end();
        return obj;
    }
}
