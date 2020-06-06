package com.hf.ciker.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

/**
 * @author ciker
 * @version 1.0
 * @date 2020/6/6 20:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 密码
     */
    private String password;

    /**
     * 系统id
     */
    private String systemId;

    /**
     * 角色集
     */
    private Set<RoleDTO> roleSet;
}
