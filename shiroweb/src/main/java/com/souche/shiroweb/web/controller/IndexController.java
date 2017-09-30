package com.souche.shiroweb.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "index";
    }

    @RequestMapping("welcome")
    public String welcome() {
        return "welcome";
    }
}
