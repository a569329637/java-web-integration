package com.souche.shiro.ch3;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

import java.util.Arrays;
import java.util.Collection;

/**
 * 用于根据角色字符串来解析得到权限集合
 */
public class MyRolePermissionResolver implements RolePermissionResolver {

    public Collection<Permission> resolvePermissionsInRole(String roleName) {
        if ("role1".equals(roleName)) {
            return Arrays.asList((Permission) new WildcardPermission("menu:*"));
        }
        return null;
    }
}
