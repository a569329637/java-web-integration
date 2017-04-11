package com.souche.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.souche.model.User;
import com.souche.service.UserServiceI;

public class UserServiceTest {

    private UserServiceI userService;
    private ApplicationContext ac;

    @Before
    public void before() {
        ac = new ClassPathXmlApplicationContext(new String[]{ "spring/spring.xml" });
        userService = (UserServiceI) ac.getBean("userService");
    }

    @Test
    public void testAddUser() {
        User user = new User();
        user.setName("admin");
        user.setAddress("杭州");

        userService.addUser(user);

        System.out.println(user);
    }

}
