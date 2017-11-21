package com.gsq.io;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Administrator on 2017/11/21.
 */
public class FileWriterOperate {

    public static void main(String[] args) {
        FileWriter fileWriter = null;
        try {
            // 创建一个文件写流对象
            fileWriter = new FileWriter("E:/io/dir1/test.txt");

            // 调用write方法将数据写入流
            fileWriter.write("hello");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 一定要在finally里关闭流，将数据清空到文件
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
