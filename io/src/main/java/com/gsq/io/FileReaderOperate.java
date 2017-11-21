package com.gsq.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Administrator on 2017/11/21.
 */
public class FileReaderOperate {

    public static void main(String[] args) {
        FileReader fileReader = null;
        try {
            // 创建一个文件读取流对象，将已经存在的一个文件加载流对象
            fileReader = new FileReader("E:/io/dir1/test.txt");

            // 创建一个存放临时数据的数组
            char[] buf = new char[1024];

            // 通过read方法读取字符流到缓冲区
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len = fileReader.read(buf)) != -1) {
                String tmp = new String(buf, 0, len);
                sb.append(tmp);
            }
            System.out.println("sb = " + sb);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 一定要在finally里关闭流
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
