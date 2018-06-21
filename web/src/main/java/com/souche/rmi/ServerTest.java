package com.souche.rmi;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author guishangquan
 * @date 2018/6/21
 */
public class ServerTest {
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("rmi/spring-rmi-server.xml");
    }
}
