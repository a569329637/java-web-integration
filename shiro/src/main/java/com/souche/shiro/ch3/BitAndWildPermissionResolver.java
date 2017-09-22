package com.souche.shiro.ch3;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

public class BitAndWildPermissionResolver implements PermissionResolver {

    public Permission resolvePermission(String s) {
        // 自定义的格式
        if (s.startsWith("+")) {
            return new BitPermission(s);
        }

        // 默认格式
        return new WildcardPermission(s);
    }
}
