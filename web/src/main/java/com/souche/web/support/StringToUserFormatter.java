package com.souche.web.support;

import org.springframework.format.Formatter;

import com.souche.model.User;

import java.text.ParseException;
import java.util.Locale;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.web.support
 * @date 17/5/4
 **/
public class StringToUserFormatter implements Formatter<User> {
    @Override
    public User parse(String s, Locale locale) throws ParseException {
        User user = new User();
        if (s != null && !s.equals("")) {
            String[] parts = s.split(":");
            user.setUserName(parts.length > 0 ? parts[0]+"（StringToUserFormatter）" : "undefined");
            user.setPassword(parts.length > 1 ? parts[1] : "undefined");
            user.setRealName(parts.length > 2 ? parts[2] : "undefined");
        }
        return user;
    }

    @Override
    public String print(User user, Locale locale) {
        return user.toString();
    }
}
