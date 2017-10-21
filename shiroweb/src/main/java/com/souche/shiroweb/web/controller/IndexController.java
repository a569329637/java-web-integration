package com.souche.shiroweb.web.controller;

import com.souche.shiroweb.entity.Resource;
import com.souche.shiroweb.entity.User;
import com.souche.shiroweb.service.ResourceService;
import com.souche.shiroweb.service.UserService;
import com.souche.shiroweb.web.bind.annotation.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

@Controller
public class IndexController {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(@CurrentUser User loginUser, Model model) {
        Set<String> permissions = userService.findPermissions(loginUser.getUsername());
        List<Resource> menus = resourceService.findMenus(permissions);
        model.addAttribute("menus", menus);
        return "index";
    }

    @RequestMapping("welcome")
    public String welcome() {
        return "welcome";
    }
}
