package com.souche.aop.proxy;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.aop.proxy
 * @date 17/4/11
 **/
public class MethodPerformance {
    private long begin;
    private long end;
    private String serviceMethod;

    public MethodPerformance(String serviceMethod) {
        begin = System.currentTimeMillis();
        this.serviceMethod = serviceMethod;
    }

    public void printPerformance() {
        end = System.currentTimeMillis();
        long elapse = end - begin;

        System.out.println(serviceMethod + "花费" + elapse + "毫秒");
    }
}
