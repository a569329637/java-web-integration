package com.gsq.io;

import java.io.*;

/**
 * Created by Administrator on 2017/11/21.
 */
public class PrintStreamOpeate {

    public static void main(String[] args) {
        FileOutputStream fileOutputStream = null;
        PrintStream printStream = null;

        try {
            fileOutputStream = new FileOutputStream("E:/io/dir1/des1.txt");
            //创建打印输出流,设置为自动刷新模式(写入换行符或字节 '\n' 时都会刷新输出缓冲区)
            printStream = new PrintStream(fileOutputStream, true);

            if (printStream != null) {
                // 把标准输出流(控制台输出)改成文件
                System.setOut(printStream);
            }

            for (int i = 0; i < 255; ++ i) {
                System.out.print((char) i);
                if (i % 50 == 0) {
                    System.out.println();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (printStream != null) {
                printStream.close();
            }
        }
    }
}
