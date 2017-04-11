package com.souche.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.souche.dao.UserMapper;
import com.souche.model.User;
import com.souche.service.UserServiceI;

@Service("userService")
public class UserServiceImpl implements UserServiceI {

    @Autowired
    private UserMapper userMapper;

    public void addUser(User user) {
        userMapper.insert(user);
    }

    public User getUserById(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

}
