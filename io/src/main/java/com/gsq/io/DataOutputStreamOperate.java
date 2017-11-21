package com.gsq.io;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/11/21.
 */
public class DataOutputStreamOperate {

    public static void main(String[] args) {
        FileOutputStream fileOutputStream = null;
        DataOutputStream dataOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream("E:/io/dir1/des2.txt");
            // 封装成数据流，更方便的操作Java的基本类型
            dataOutputStream = new DataOutputStream(fileOutputStream);

            // 关于中文乱码的问题可以将dataOutputStream封装成OutputStreamWriter并设置编码，使用OutputStreamWriter对象写入文件
            dataOutputStream.writeUTF("as中文");
            dataOutputStream.writeBoolean(false);
            dataOutputStream.writeLong(2356723432L);
            // dataOutputStream.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (dataOutputStream != null) {
                try {
                    dataOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
