package com.hf.ciker.user.dao;

import com.hf.ciker.user.po.User;
import com.hf.ciker.user.po.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    /**
     * 增加用户
     * @param record
     * @return
     */
    int insert(User record);

    /**
     * 查询用户
     * @param userId
     * @param isDelete
     * @return
     */
    User findUser(@Param("userId") String userId, @Param("isDelete") String isDelete);

    /**
     * 更新密码或者使用户失效
     * @param record
     * @return
     */
    int updateUser(User record);

    /**
     * 关联角色
     * @param userRole
     * @return
     */
    int relevanceRole(UserRole userRole);

    /**
     * 收回用户角色
     * @param userRole
     * @return
     */
    int deleteUserRole(UserRole userRole);

    /**
     * 查找systemId对应系统当前用户的所有角色id
     * @param userId
     * @return
     */
    List<UserRole> findUserRoles(@Param("userId") String userId, @Param("systemId") String systemId);


}