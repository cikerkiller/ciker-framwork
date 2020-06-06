package com.hf.ciker.user.dto;

import lombok.Data;

/**
 * @author ciker
 * @version 1.0
 * @date 2020/6/6 20:36
 */
@Data
public class PermissionDTO {
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
