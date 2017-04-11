package com.souche.aop.proxy;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.aop.proxy
 * @date 17/4/11
 **/
public class PerformanceMonitor {

    private static ThreadLocal<MethodPerformance> performance = new ThreadLocal<MethodPerformance>();

    public static void begin(String method) {
        System.out.println("begin monitor ...");

        MethodPerformance mp = new MethodPerformance(method);

        performance.set(mp);
    }

    public static void end() {
        System.out.println("end monitor ...");

        MethodPerformance mp = performance.get();

        mp.printPerformance();
    }
}
