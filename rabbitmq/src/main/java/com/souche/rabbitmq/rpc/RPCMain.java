package com.souche.rabbitmq.rpc;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2017/11/18.
 * RPC 远程调用
 */
public class RPCMain {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        RPCClient client = new RPCClient();
        String res = client.call("10");
        System.out.println("res = " + res);
        client.close();
    }
}
