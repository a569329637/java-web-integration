package com.gsq.nio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author guishangquan
 * @date 2018/2/12
 */
public class BioClient implements Runnable {

    @Override
    public void run() {
        try {
            Socket socket = new Socket("127.0.0.1", 8080);
            OutputStream outputStream = socket.getOutputStream();

            String content = Thread.currentThread().getName() + "..........qwertyuiop";
            outputStream.write(content.getBytes());

            System.out.println("##" + content);
            System.out.println("写入完毕");
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; ++ i) {
            Thread thread = new Thread(new BioClient());
            thread.start();
        }
    }

}
