package com.souche.aop.service.impl;

import com.souche.aop.proxy.PerformanceMonitor;
import com.souche.aop.service.ForumService;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.aop.service.impl
 * @date 17/4/11
 **/
public class ForumServiceImpl implements ForumService {

    public void removeTopic(int topicId) {
        PerformanceMonitor.begin(getClass().getName() + "removeTopic");
        System.out.println("模拟删除Topic记录: " + topicId);

        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        PerformanceMonitor.end();
    }

    public void removeForum(int topicId) {
        PerformanceMonitor.begin(getClass().getName() + "removeTopic");
        System.out.println("模拟删除Topic记录: " + topicId);

        try {
            Thread.sleep(40);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        PerformanceMonitor.end();
    }

    public static void main(String[] args) {
        ForumService forumService = new ForumServiceImpl();
        forumService.removeTopic(1);
        forumService.removeForum(2);
    }
}
