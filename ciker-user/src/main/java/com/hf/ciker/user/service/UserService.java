package com.hf.ciker.user.service;

import com.hf.ciker.user.dto.UserDTO;
import com.hf.ciker.user.po.User;

/**
 * @author ciker
 * @version 1.0
 * @date 2020/6/6 13:10
 */
public interface UserService {

    User findEffectiveUser(String userId);

    /**
     * logout
     * @param token
     */
    void logout(String token);

    UserDTO findUserRolePermission(String userId, String systemId);
}
