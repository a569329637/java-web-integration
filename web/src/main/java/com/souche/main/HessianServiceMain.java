package com.souche.main;

import com.caucho.hessian.client.HessianProxyFactory;
import com.souche.hessian.HessianService;
import com.souche.model.User;

import java.net.MalformedURLException;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.main
 * @date 17/5/22
 **/
public class HessianServiceMain {
    public static void main(String[] args) {
        String url = "http://localhost:8080/ServiceServlet";
        HessianProxyFactory factory = new HessianProxyFactory();
        HessianService hessianService = null;
        try {
            hessianService = (HessianService) factory.create(HessianService.class, url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        User data = hessianService.getData();
        System.out.println("data = " + data);
    }
}
