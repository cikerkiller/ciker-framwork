package com.hf.ciker.user.dao;

import com.hf.ciker.user.po.Permission;
import com.hf.ciker.user.po.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapper {

    /**
     * 批量查询权限
     * @param rolePermissionList
     * @return
     */
    List<Permission> findPermissions(@Param("rolePermissionList") List<RolePermission> rolePermissionList);
}