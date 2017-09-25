package com.souche.shiro.ch6.service;

import com.souche.shiro.ch6.entity.Permission;

public interface PermissionService {

    public Permission createPermission(Permission permission);
    public void deletePermission(Long permissionId);

}
