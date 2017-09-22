package com.souche.shiro.ch2;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

public class MyRealm2 implements Realm {

    public String getName() {
        return "myrealm2";
    }

    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取用户名
        String username = (String) authenticationToken.getPrincipal();
        // 获取密码
        String password = new String((char[])authenticationToken.getCredentials());

        if (!"zhang".equals(username)) {
            // 如果用户名错误
            throw new UnknownAccountException();
        }
        if (!"123".equals(password)) {
            // 如果密码错误
            throw new IncorrectCredentialsException();
        }

        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
