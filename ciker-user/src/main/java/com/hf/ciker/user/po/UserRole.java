package com.hf.ciker.user.po;

import lombok.Data;

/**
 * 用户角色关联表
 */
@Data
public class UserRole extends BasePO {

    /**
     * 用户表主键id
     */
    private String userId;

    /**
     * 角色表主键id
     */
    private String idCkRole;

    /**
     * 系统id
     */
    private String systemId;
}