package com.souche.dubbo;

import com.souche.facade.DubboApiService;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.dubbo
 * @date 17/5/20
 **/
public class DubboApiServiceImpl implements DubboApiService {
    @Override
    public void sayHello(String msg) {
        System.out.println("invoke dubbo service: " + this.getClass().getName());
        System.out.println("msg = " + msg);
    }

    @Override
    public String getData() {
        System.out.println("invoke dubbo service: " + this.getClass().getName());
        return "this is data";
    }
}
