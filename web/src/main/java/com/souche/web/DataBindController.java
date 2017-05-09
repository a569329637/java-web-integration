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
        // Date 类型的数据绑定到方法入参都是 "yyyy-MM-dd" 这种格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping("/handle1")
    public String handle1(Date date, User user) {
        // 也可以在 User 类中使用注解驱动格式化
        System.out.println("date = " + date);
        System.out.println("date.toLocaleString() = " + date.toLocaleString());
        System.out.println("user = " + user);
        return "/user/register";
    }


}
