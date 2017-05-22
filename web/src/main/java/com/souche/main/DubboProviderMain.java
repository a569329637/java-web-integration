package com.souche.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.main
 * @date 17/5/20
 **/
public class DubboProviderMain {
    // 测试时候记得把依赖取消注释
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring-dubbo-provider.xml");
        ac.start();
        System.out.println("按任意键退出");
        System.in.read();
    }
}
