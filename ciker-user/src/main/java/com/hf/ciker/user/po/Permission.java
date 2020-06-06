package com.hf.ciker.user.po;

import lombok.Data;

/**
 * 权限表
 */
@Data
public class Permission extends BasePO{

    /**
     * 权限表主键
     */
    private String idCkPermission;

    /**
     * 权限代码
     */
    private String permissionCode;

    /**
     * 权限描述
     */
    private String permissionDesc;

}