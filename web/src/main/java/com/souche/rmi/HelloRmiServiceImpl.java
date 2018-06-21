package com.souche.rmi;

/**
 * @author guishangquan
 * @date 2018/6/21
 */
public class HelloRmiServiceImpl implements HelloRmiService {
    @Override
    public int add(int a, int b) {
        return a + b;
    }
}
