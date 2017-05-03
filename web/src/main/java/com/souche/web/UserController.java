package com.souche.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.souche.model.User;
import com.souche.service.UserServiceI;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.OutputStream;

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
        // 请求地址为 /user/register
        System.out.println("true = " + true);
        return "user/register";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createUser(User user) {
        // 请求地址 /user/create
        // 请求方法 post
        System.out.println("user = " + user);
        userServiceI.addUser(user);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("/user/createSuccess");
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping("/test/{pathId}")
    public String testPath(@PathVariable Integer pathId) {
        // 请求地址 /test/{pathId}
        // 请求参数 请求路径参数入参 pathId
        System.out.println("pathId = " + pathId);
        return "/user/register";
    }

    @RequestMapping(value = "/test", params = {"param1!=value1", "param2=value2"})
    public String testParams(String param1, String param2) {
        // 请求地址 /test
        // 请求参数 param1 不能等于 value1 ，param2 必须等于 value2
        // headers 与 params 类似
        System.out.println("param1 = " + param1);
        System.out.println("param2 = " + param2);
        return "/user/register";
    }

    @RequestMapping("/handle1")
    public String handle1(@RequestParam("userName") String userName,
                          @RequestParam String password,
                          @RequestParam String realName) {
        // @RequestParam 绑定参数到方法入参
        System.out.println("userName = " + userName);
        System.out.println("password = " + password);
        System.out.println("realName = " + realName);
        return "/user/register";
    }

    @RequestMapping("/handle2")
    public String handle2(@CookieValue("JSESSIONID") String sessionId,
                          @RequestHeader("Accept-Language") String acceptLanguage) {
        // @CookieValue 将 cookie 值绑定入参
        // @RequestHeader 将请求头值绑定入参
        System.out.println("sessionId = " + sessionId);
        System.out.println("acceptLanguage = " + acceptLanguage);
        return "/user/register";
    }

    @RequestMapping("/handle3")
    public String handle3(HttpServletRequest request) {// WebRequest, NativeRequest, HttpSession
        // 将 http 请求对象传递给入参
        System.out.println("request = " + request);
        return "/user/register";
    }

    @RequestMapping("/handle4")
    public void handle4(OutputStream os) {
        // 绑定输出流到参数
        Resource resource = new ClassPathResource("/images/image.jpg");
        try {
            FileCopyUtils.copy(resource.getInputStream(), os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/handle5")
    public String handle5(@RequestBody String requestBody) {
        // http://blog.csdn.net/walkerjong/article/details/7520896
        System.out.println("requestBody = " + requestBody);
        return "/user/register";
    }

    @ResponseBody
    @RequestMapping("/handle6")
    public byte[] handle6() {
        Resource resource = new ClassPathResource("/images/image.jpg");
        byte[] bytes = new byte[0];
        try {
            bytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    @RequestMapping("/handle7")
    public String handle7(HttpEntity<String> httpEntity) {
        // 使用 StringHttpMessageConverter
        Long contentLength = httpEntity.getHeaders().getContentLength();
        System.out.println("contentLength = " + contentLength);
        return "/user/register";
    }

    @RequestMapping("/handle8")
    public ResponseEntity<byte[]> handle8() {
        // 使用 ByteArrayHttpMessageConverter
        Resource resource = new ClassPathResource("/images/image.jpg");
        byte[] bytes = new byte[0];
        try {
            bytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(bytes, HttpStatus.OK);
        return responseEntity;
    }

//    @RequestMapping("/handle9")
//    public ResponseEntity<User> handle9(HttpEntity<User> requestEntity) {
//        User user = requestEntity.getBody();
//        user.setId(1000L);
//        return new ResponseEntity<User>(user, HttpStatus.OK);
//    }

    @RequestMapping("/handle10")
    public String handle10(@ModelAttribute User user) {
        System.out.println("user = " + user);
        return "/user/register";
    }

}
