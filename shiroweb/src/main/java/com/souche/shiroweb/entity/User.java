package com.souche.shiroweb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements Serializable {
    private Long id; //编号
    private Long organizationId; //所属公司
    private String username; //用户名
    private String password; //密码
    private String salt; //加密密码的盐
    private List<Long> roleIds; //拥有的角色列表
    private Boolean locked = Boolean.FALSE;

    public String getRoleIdsStr() {
        if(CollectionUtils.isEmpty(roleIds)) {
            return "";
        }
        StringBuilder s = new StringBuilder();
        for(Long roleId : roleIds) {
            s.append(roleId);
            s.append(",");
        }
        return s.toString();
    }

    public void setRoleIdsStr(String roleIdsStr) {
        if(StringUtils.isEmpty(roleIdsStr)) {
            return;
        }
        String[] roleIdStrs = roleIdsStr.split(",");
        for(String roleIdStr : roleIdStrs) {
            if(StringUtils.isEmpty(roleIdStr)) {
                continue;
            }
            getRoleIds().add(Long.valueOf(roleIdStr));
        }
    }
}
