package com.souche.web.support;

import com.souche.model.User;

import java.beans.PropertyEditorSupport;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.web.support
 * @date 17/5/4
 **/
public class UserEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        User user = new User();
        if (text != null && !text.equals("")) {
            String[] parts = text.split(":");
            user.setUserName(parts.length > 0 ? parts[0] : "undefined");
            user.setPassword(parts.length > 1 ? parts[1] : "undefined");
            user.setRealName(parts.length > 2 ? parts[2] : "undefined");
        }
        this.setValue(user);
    }
}
