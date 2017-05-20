package com.souche.facade;

import request.DubboReqeust;
import response.DubboResponse;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.facade
 * @date 17/5/20
 **/
public interface DubboApiFacade {
    DubboResponse getData(DubboReqeust reqeust);
}
