package com.souche.shiro.ch3;

import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.PrincipalCollection;
import org.junit.Assert;
import org.junit.Test;

public class PermissionTest extends BaseTest {

    @Test
    public void testIsPermitted() {
        login("classpath:ch3/shiro-permission.ini", "zhang", "123");
        //判断拥有权限：user:create
        Assert.assertTrue(subject().isPermitted("user:create"));
        //判断拥有权限：user:update and user:delete
        Assert.assertTrue(subject().isPermittedAll("user:update", "user:delete"));
        //判断没有权限：user:view
        Assert.assertFalse(subject().isPermitted("user:view"));
    }

    @Test(expected = UnauthorizedException.class)
    public void testCheckPermission () {
        login("classpath:ch3/shiro-permission.ini", "zhang", "123");
        //断言拥有权限：user:create
        subject().checkPermission("user:create");
        //断言拥有权限：user:delete and user:update
        subject().checkPermissions("user:delete", "user:update");
        //断言拥有权限：user:view 失败抛出异常
        subject().checkPermissions("user:view");
    }

    @Test
    public void testSystemIsPermitted() {
        login("classpath:ch3/shiro-authorizer-system.ini", "zhang", "123");

        //判断拥有权限：user1:*
        Assert.assertTrue(subject().isPermitted("user1:*"));
        //判断拥有权限：user2:*
        Assert.assertTrue(subject().isPermitted("user2:*"));
        //判断拥有权限：user2:create
        Assert.assertTrue(subject().isPermitted("user2:create"));
        //判断没有权限：user3:*
        Assert.assertFalse(subject().isPermitted("user3:*"));

        // 打印认证信息
        PrincipalCollection principals = subject().getPrincipals();
        System.out.println("principals = " + principals);
    }

    @Test
    public void testCustomIsPermitted() {
        login("classpath:ch3/shiro-authorizer-custom.ini", "zhang", "123");

        //判断拥有权限：user1:*
        Assert.assertTrue(subject().isPermitted("user1:*"));
        //判断拥有权限：user2:*
        Assert.assertTrue(subject().isPermitted("user2:*"));
        //判断拥有权限：user2:create
        Assert.assertTrue(subject().isPermitted("user2:create"));
        //判断没有权限：user3:*
        Assert.assertFalse(subject().isPermitted("user3:*"));

        // 打印认证信息
        PrincipalCollection principals = subject().getPrincipals();
        System.out.println("principals = " + principals);

        // 通过二进制位的方式表示权限
        Assert.assertTrue(subject().isPermitted("+user1+2"));//新增权限
        Assert.assertTrue(subject().isPermitted("+user1+8"));//查看权限
        Assert.assertTrue(subject().isPermitted("+user2+10"));//新增及查看
        Assert.assertFalse(subject().isPermitted("+user1+4"));//没有删除权限

        // 通过MyRolePermissionResolver解析得到的权限
        Assert.assertTrue(subject().isPermitted("menu:view"));
    }

}
