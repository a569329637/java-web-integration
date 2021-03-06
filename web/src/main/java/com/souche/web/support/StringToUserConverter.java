package com.souche.web.support;

import com.souche.model.User;
import org.springframework.core.convert.converter.Converter;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.web.support
 * @date 17/5/4
 **/
public class StringToUserConverter implements Converter<String, User> {
    @Override
    public User convert(String s) {
        User user = new User();
        if (s != null && !s.equals("")) {
            String[] parts = s.split(":");
            user.setUserName(parts.length > 0 ? parts[0]+"（StringToUserConverter）" : "undefined");
            user.setPassword(parts.length > 1 ? parts[1] : "undefined");
            user.setRealName(parts.length > 2 ? parts[2] : "undefined");
        }
        return user;
    }
}
