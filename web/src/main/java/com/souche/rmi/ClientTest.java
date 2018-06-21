package com.souche.rmi;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author guishangquan
 * @date 2018/6/21
 */
public class ClientTest {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("rmi/spring-rmi-client.xml");
        HelloRmiService myClient = ac.getBean("myClient", HelloRmiService.class);
        int add = myClient.add(1, 5);
        System.out.println("add = " + add);
    }
}
