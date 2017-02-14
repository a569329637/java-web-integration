package com.souche.service;

import com.souche.model.User;

public interface UserServiceI {

    void addUser(User user);

    User getUserById(Long userId);

}