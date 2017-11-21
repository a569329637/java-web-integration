package com.gsq.io;

import java.io.*;

/**
 * Created by Administrator on 2017/11/21.
 */
public class InputOutputStreamReaderWriter {

    public static void main(String[] args) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try {
            // 创建文件输入输出字节流
            fileInputStream = new FileInputStream("E:/io/dir1/src.txt");
            fileOutputStream = new FileOutputStream("E:/io/dir1/des.txt");

            // 下面两个类能够将字节流转换成字符流，并且可以设置字符编码
            // 将文件输入输出字节流转为字符流
            inputStreamReader = new InputStreamReader(fileInputStream, "GBK");
            outputStreamWriter = new OutputStreamWriter(fileOutputStream, "GBK");

            // 将字符流在包装成缓冲流
            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            String tmp;
            while ((tmp = bufferedReader.readLine()) != null) {
                bufferedWriter.write(tmp);
                System.out.println("tmp = " + tmp);
                bufferedWriter.newLine();
            }

            bufferedWriter.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStreamWriter != null) {
                try {
                    outputStreamWriter.close();
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
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
