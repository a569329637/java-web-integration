package com.souche.aop.advice;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.aop.advice
 * @date 17/4/14
 **/
public class NaiveWaiter implements Waiter {

    public void greetTo(String name) {
        System.out.println("greet to " + name + " ...");
    }

    public void serveTo(String name) {
        System.out.println("serve to " + name + " ...");
    }

    public void exceptionTest() {
        throw new RuntimeException("数据更新异常啦！");
    }
}
