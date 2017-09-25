package com.souche.shiro.ch6.service;

import com.souche.shiro.ch6.dao.PermissionDao;
import com.souche.shiro.ch6.dao.PermissionDaoImpl;
import com.souche.shiro.ch6.entity.Permission;

public class PermissionServiceImpl implements PermissionService {

    private PermissionDao permissionDao = new PermissionDaoImpl();

    public Permission createPermission(Permission permission) {
        return permissionDao.createPermission(permission);
    }

    public void deletePermission(Long permissionId) {
        permissionDao.deletePermission(permissionId);
    }

}
