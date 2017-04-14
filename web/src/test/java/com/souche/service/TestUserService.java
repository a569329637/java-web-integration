package com.souche.service;

import com.souche.BaseTest;
import com.souche.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestUserService extends BaseTest {

    @Autowired
    private UserServiceI userService;

    @Test
    public void testAddUser() {
        User user = new User();
        user.setName("admin");
        user.setAddress("杭州");

        userService.addUser(user);

        System.out.println(user);
    }

}
