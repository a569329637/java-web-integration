package com.souche.aop.service.impl;

import com.souche.aop.proxy.CglibProxy;
import com.souche.aop.service.ForumService;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.aop.service.impl
 * @date 17/4/11
 **/
public class ForumServiceImpl implements ForumService {

    public void removeTopic(int topicId) {
//        PerformanceMonitor.begin(getClass().getName() + "removeTopic");
        System.out.println("模拟删除Topic记录: " + topicId);

        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        PerformanceMonitor.end();
    }

    public void removeForum(int topicId) {
//        PerformanceMonitor.begin(getClass().getName() + "removeTopic");
        System.out.println("模拟删除Topic记录: " + topicId);

        try {
            Thread.sleep(40);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        PerformanceMonitor.end();
    }

    public static void main(String[] args) {
        // 没有删除 PerformanceMonitor.begin 和 PerformanceMonitor.end 时
//        ForumService forumService = new ForumServiceImpl();
//        forumService.removeTopic(1);
//        forumService.removeForum(2);

        // 实现 InvocationHandler 接口，动态代理 ForumService
//        ForumService target = new ForumServiceImpl();
//        PerformanceHandler performanceHandler = new PerformanceHandler(target);
//        ForumService proxyTarget = (ForumService) Proxy.newProxyInstance(
//                target.getClass().getClassLoader(),
//                target.getClass().getInterfaces(),
//                performanceHandler);
//        proxyTarget.removeForum(1);
//        proxyTarget.removeTopic(2);

        // 通过 cglib 动态代理生成代理对象 forumServiceImpl
        CglibProxy cglibProxy = new CglibProxy();
        ForumServiceImpl forumServiceImpl = (ForumServiceImpl) cglibProxy.getProxy(ForumServiceImpl.class);
        forumServiceImpl.removeForum(1);
        forumServiceImpl.removeTopic(2);
    }
}
