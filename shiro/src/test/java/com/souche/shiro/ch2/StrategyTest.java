package com.souche.shiro.ch2;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

public class StrategyTest extends StrategyBaseTest {

    @Test
    public void testAllSuccessfulStrategyWithSuccess() {
        login("classpath:ch2/shiro-authenticator-all-success.ini");
        Subject subject = SecurityUtils.getSubject();

        // 得到一个身份集合，其包含了Realm验证成功的身份信息
        PrincipalCollection principals = subject.getPrincipals();
        Assert.assertEquals(2, principals.asList().size());
        // 这里得到两个身份
        System.out.println("principals = " + principals);
    }

    @Test
    public void testAllSuccessfulStrategyWithFail() {
        try {
            login("classpath:ch2/shiro-authenticator-all-fail.ini");
        } catch (UnknownAccountException e) {
        }
        Subject subject = SecurityUtils.getSubject();

        // 得到一个身份集合，其包含了Realm验证成功的身份信息
        PrincipalCollection principals = subject.getPrincipals();
        Assert.assertNull(principals);
        // 这里没有得到身份
        System.out.println("principals = " + principals);
    }

    @Test
    public void testFirstSuccessStrategy() {
        login("classpath:ch2/shiro-authenticator-first-success.ini");
        Subject subject = SecurityUtils.getSubject();

        // 得到一个身份集合，其包含了Realm验证成功的身份信息
        PrincipalCollection principals = subject.getPrincipals();
        Assert.assertEquals(1, principals.asList().size());
        // 这里得到两个身份
        System.out.println("principals = " + principals);
    }

    @Test
    public void testAtLastOneSuccessStrategy() {
        login("classpath:ch2/shiro-authenticator-at-last-one-success.ini");
        Subject subject = SecurityUtils.getSubject();

        // 得到一个身份集合，其包含了Realm验证成功的身份信息
        PrincipalCollection principals = subject.getPrincipals();
        Assert.assertEquals(2, principals.asList().size());
        // 这里得到两个身份
        System.out.println("principals = " + principals);
    }

}
