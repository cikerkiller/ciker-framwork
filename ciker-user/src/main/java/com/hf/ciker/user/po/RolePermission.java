package com.hf.ciker.user.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色权限关联表
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RolePermission extends BasePO {

    /**
     * 角色表主键id
     */
    private String idCkRole;

    /**
     * 权限表主键
     */
    private String idCkPermission;

    /**
     * 系统id
     */
    private String systemId;
}