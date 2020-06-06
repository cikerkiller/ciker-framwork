package com.hf.ciker.user.service.impl;

import com.hf.ciker.user.dao.PermissionMapper;
import com.hf.ciker.user.dao.RoleMapper;
import com.hf.ciker.user.dao.UserMapper;
import com.hf.ciker.user.dto.IS;
import com.hf.ciker.user.dto.PermissionDTO;
import com.hf.ciker.user.dto.RoleDTO;
import com.hf.ciker.user.dto.UserDTO;
import com.hf.ciker.user.po.*;
import com.hf.ciker.user.service.TokenService;
import com.hf.ciker.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.util.Assert;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ciker
 * @version 1.0
 * @date 2020/6/6 13:10
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private TokenService tokenService;

    @Override
    public User findEffectiveUser(String userId) {
        return userMapper.findUser(userId, IS.N.getValue());
    }

    @Override
    public void logout(String token) {
        tokenService.loseEfficacyToken(token);
    }

    @Override
    public UserDTO findUserRolePermission(String userId, String systemId) {
        User user = findEffectiveUser(userId);
        List<UserRole> userRoles = userMapper.findUserRoles(userId, systemId);
        Assert.notEmpty(userRoles);
        List<Role> roleList = roleMapper.findRoles(userRoles);
        Assert.notEmpty(roleList);
        List<RolePermission> rolePermissions = roleMapper.findRolePermissions(roleList, systemId);
        Assert.notEmpty(rolePermissions);
        List<Permission> permissions = permissionMapper.findPermissions(rolePermissions);
        Assert.notEmpty(permissions);
        Set<RoleDTO> roleDTOList =  new HashSet<>();
        roleList.forEach(role -> {
            RoleDTO roleDTO = new RoleDTO();
            BeanUtils.copyProperties(role,roleDTO);
            Set<PermissionDTO> permissionDTOS =  new HashSet<>();
            Set<String> permissionIds = new HashSet<>();
            rolePermissions.forEach(rolePermission -> {
                if(StringUtils.equals(roleDTO.getIdCkRole(),rolePermission.getIdCkRole())){
                    permissionIds.add(rolePermission.getIdCkPermission());
                }
            });
            permissions.forEach(permission -> {
                PermissionDTO permissionDTO = new PermissionDTO();
                BeanUtils.copyProperties(permission,permissionDTO);
                if(permissionIds.contains(permissionDTO.getIdCkPermission())){
                    permissionDTOS.add(permissionDTO);
                }
            });
            roleDTO.setPermissionSet(permissionDTOS);
            roleDTOList.add(roleDTO);
        });
        return new UserDTO(userId,user.getPassword(),systemId,roleDTOList);
    }
}
