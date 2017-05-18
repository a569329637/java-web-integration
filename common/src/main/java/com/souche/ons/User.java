package com.souche.ons;

import lombok.Data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.ons
 * @date 17/5/18
 **/
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 6791999291720834452L;
    private String username;
    private String password;

    public static void main(String[] args) {
        User user = new User();
        user.setUsername("a569329637");
        user.setPassword("123456");

        ByteArrayOutputStream obj = new ByteArrayOutputStream();
        try {
            ObjectOutputStream out = new ObjectOutputStream(obj);
            out.writeObject(user);
            byte[] bytes = obj.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
