package com.gsq.io;

import java.io.*;

/**
 * Created by Administrator on 2017/11/21.
 */
public class ObjectOutputStreamOperate {

    public static void main(String[] args) {
        write();
        read();
    }

    private static void write() {
        ObjectOutputStream objectOutputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream("E:/io/dir1/des3.txt");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            Person person = new Person();
            person.setName("哈哈");
            person.setAge(11);
            person.setAddress("红红火火恍恍惚惚");

            objectOutputStream.writeObject(person);
            objectOutputStream.flush();

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
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void read() {
        ObjectInputStream objectInputStream = null;
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream("E:/io/dir1/des3.txt");
            objectInputStream = new ObjectInputStream(fileInputStream);

            Object o = objectInputStream.readObject();
            Person person = (Person) o;

            System.out.println("person = " + person);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
