package com.souche.web.support;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import com.souche.model.User;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.web.support
 * @date 17/5/4
 **/
public class MyBindingInitializer implements WebBindingInitializer {
    @Override
    public void initBinder(WebDataBinder webDataBinder, WebRequest webRequest) {
        webDataBinder.registerCustomEditor(User.class, new UserEditor());
    }
}
