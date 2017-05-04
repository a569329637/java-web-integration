package com.souche.web;

import com.souche.model.User;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.web
 * @date 17/5/4
 **/
@Controller
@RequestMapping("/dataBind")
public class DataBindController {

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping("/handle1")
    public String handle1(Date date, User user) {
        System.out.println("date = " + date);
        System.out.println("user = " + user);
        return "/user/register";
    }
}
