package com.souche.hessian.impl;

import com.souche.hessian.HessianService;
import com.souche.model.User;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.hessian.impl
 * @date 17/5/22
 **/
public class HessianServiceImpl implements HessianService {
    @Override
    public User getData() {
        User user = new User();
        user.setUserName("a569329637");
        user.setRealName("guishangquan");
        user.setPassword("123456");
        return user;
    }
}
