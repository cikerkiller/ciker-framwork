package com.hf.ciker.user.dto;

import lombok.Data;

import java.util.Set;

/**
 * @author ciker
 * @version 1.0
 * @date 2020/6/6 20:35
 */
@Data
public class RoleDTO {

    /**
     * ck_role 表主键
     */
    private String idCkRole;

    /**
     * 角色代码
     */
    private String roleCode;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 权限集
     */
    private Set<PermissionDTO> permissionSet;
}
