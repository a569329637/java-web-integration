package com.gsq.io;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2017/11/21.
 */
public class FileOperate {

    public static void main(String[] args) throws IOException {
        File dir1 = new File("E:/io/dir1");
        if (!dir1.exists()) {
            // 该方法不能创建多层目录
            // boolean mkdir = dir1.mkdir();
            boolean mkdirs = dir1.mkdirs();
            System.out.println("mkdirs = " + mkdirs);
        }

        // 创建一个以dir1为父目录，文件名为test.txt的文件
        File file1 = new File(dir1, "test.txt");
        if (!file1.exists()) {
            file1.createNewFile();
        }
    }

}
