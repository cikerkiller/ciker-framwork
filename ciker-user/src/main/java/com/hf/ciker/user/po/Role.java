package com.hf.ciker.user.po;


import lombok.Data;

/**
 * 角色表
 */
@Data
public class Role extends BasePO{

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

}