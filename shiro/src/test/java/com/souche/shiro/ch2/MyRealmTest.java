package com.souche.shiro.ch2;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

public class MyRealmTest {

    @Test
    public void testCustomRealm() {
        // 获取 SecurityManager 工厂，通过 shiro-realm.ini 文件创建，会自动加载 MyRealm1.java 文件
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:ch2/shiro-realm.ini");
        // 获取 SecurityManager 实例
        SecurityManager instance = factory.getInstance();
        //
        SecurityUtils.setSecurityManager(instance);
        // 得到验证对象 subject
        Subject subject = SecurityUtils.getSubject();
        // 通过用户名和密码创建 token
        UsernamePasswordToken token = new UsernamePasswordToken("gui", "123");

        try {
            // 登录
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        // 判断是否已经登录
        Assert.assertTrue(subject.isAuthenticated());
        System.out.println("subject = " + subject.isAuthenticated());

        // 登出
        subject.logout();
    }
}
