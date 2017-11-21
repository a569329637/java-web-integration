package com.gsq.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2017/11/21.
 */
public class SystemInOutOperate {

    public static void main(String[] args) {
        // 将标准输入流这个字节流转字符流
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        // 再包装成缓冲流
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String tmp = null;
        try {
            while ((tmp = bufferedReader.readLine()) != null) {
                if (tmp.equalsIgnoreCase("e") || tmp.equalsIgnoreCase("exit")) {
                    System.out.println("安全退出!");
                    break;
                }
                System.out.println("tmp.toUpperCase() = " + tmp.toUpperCase());
                System.out.println("继续输入信息");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
