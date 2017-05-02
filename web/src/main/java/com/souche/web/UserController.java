package com.souche.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.souche.model.User;
import com.souche.service.UserServiceI;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.web
 * @date 17/5/2
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceI userServiceI;

    @RequestMapping("/register")
    public String register() {
        System.out.println("true = " + true);
        return "/user/register";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createUser(User user) {
        System.out.println("user = " + user);
        userServiceI.addUser(user);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("/user/createSuccess");
        mav.addObject("user", user);
        return mav;
    }

}
