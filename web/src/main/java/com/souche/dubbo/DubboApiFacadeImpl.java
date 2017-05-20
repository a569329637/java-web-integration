package com.souche.dubbo;

import com.souche.facade.DubboApiFacade;
import request.DubboReqeust;
import response.DubboResponse;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.dubbo
 * @date 17/5/20
 **/
public class DubboApiFacadeImpl implements DubboApiFacade {
    @Override
    public DubboResponse getData(DubboReqeust reqeust) {
        System.out.println("invoke dubbo service: " + this.getClass().getName());
        System.out.println("reqeust = " + reqeust);
        DubboResponse response = new DubboResponse();
        response.setData("hahah");
        return response;
    }
}
