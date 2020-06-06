package com.hf.ciker.user.dao;

import com.hf.ciker.user.po.Role;
import com.hf.ciker.user.po.RolePermission;
import com.hf.ciker.user.po.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {

    /**
     * 收回角色的权限
     * @param rolePermission
     * @return
     */
    int deletePermission(RolePermission rolePermission);

    /**
     * 给角色关联权限
     * @param rolePermission
     * @return
     */
    int relevancePermission(RolePermission rolePermission);

    /**
     * 根据用户关联的角色id查询角色信息
     * @param userRoleList
     * @return
     */
    List<Role> findRoles(@Param("userRoleList") List<UserRole> userRoleList);

    /**
     * 批量查询角色权限
     * @param roleList
     * @param systemId
     * @return
     */
    List<RolePermission> findRolePermissions(@Param("roleList") List<Role> roleList, @Param("systemId") String systemId);

}