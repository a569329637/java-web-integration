package com.souche.main;

import com.souche.facade.DubboApiFacade;
import com.souche.facade.DubboApiService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import request.DubboReqeust;
import response.DubboResponse;

import java.io.IOException;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.main
 * @date 17/5/20
 **/
public class DubboConsumerMain {
    // 测试时候记得把依赖取消注释
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring-dubbo-consumer.xml");
        DubboApiService dubboApiService = (DubboApiService) ac.getBean("dubboApiService");
        dubboApiService.sayHello(" xxxhhhyyy!");

        String data = dubboApiService.getData();
        System.out.println("data = " + data);

        DubboApiFacade dubboApiFacade = (DubboApiFacade) ac.getBean("dubboApiFacade");
        DubboReqeust reqeust = new DubboReqeust();
        reqeust.setId("110");
        reqeust.setName("某某某");
        DubboResponse response = dubboApiFacade.getData(reqeust);
        System.out.println("response = " + response);
    }
}
