package com.gsq.io;

import java.io.*;

/**
 * Created by Administrator on 2017/11/21.
 */
public class BufferedReaderWriterOperate {

    public static void main(String[] args) {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            bufferedReader = new BufferedReader(new FileReader("E:/io/dir1/src.txt"));
            bufferedWriter = new BufferedWriter(new FileWriter("E:/io/dir1/des.txt"));

            String tmp = null;
            while ((tmp = bufferedReader.readLine()) != null) {
                bufferedWriter.write(tmp);
                System.out.println("tmp = " + tmp);
                bufferedWriter.newLine();
            }

            // bufferedWriter.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedWriter != null) {
                try {
                    // close的时候会自动flush一次
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
