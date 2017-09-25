package com.souche.shiro.ch6.dao;

import com.souche.shiro.ch6.entity.Role;

public interface RoleDao {

    Role createRole(Role role);
    void deleteRole(Long roleId);

    void correlationPermissions(Long roleId, Long... permissionIds);
    void uncorrelationPermissions(Long roleId, Long... permissionIds);

}
