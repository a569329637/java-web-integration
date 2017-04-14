package com.souche.aop.advisor;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.aop.advisor
 * @date 17/4/14
 **/
public class Waiter {

    public void greetTo(String name) {
        System.out.println("waiter greet to " + name + " ...");
    }

    public void serveTo(String name) {
        System.out.println("waiter serving to " + name + " ...");
    }

}
