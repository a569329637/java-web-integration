package com.souche.shiro.ch6.dao;

import com.souche.shiro.ch6.entity.Permission;

public interface PermissionDao {

    Permission createPermission(Permission permission);
    void deletePermission(Long permissionId);

}
