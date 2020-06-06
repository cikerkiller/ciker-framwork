package com.hf.ciker.user.shiro;

import com.hf.ciker.user.dto.PermissionDTO;
import com.hf.ciker.user.dto.RoleDTO;
import com.hf.ciker.user.dto.UserDTO;
import com.hf.ciker.user.po.Token;
import com.hf.ciker.user.po.User;
import com.hf.ciker.user.service.TokenService;
import com.hf.ciker.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Override
    /**
     * 授权 获取用户的角色和权限
     *@param  [principals]
     *@return org.apache.shiro.authz.AuthorizationInfo
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //1. 从 PrincipalCollection 中来获取登录用户的信息
        UserDTO userRolePermission = (UserDTO) principals.getPrimaryPrincipal();
        userRolePermission = userService.findUserRolePermission(userRolePermission.getUserId(), userRolePermission.getSystemId());
        //2.添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (RoleDTO role : userRolePermission.getRoleSet()) {
            //2.1添加角色
            simpleAuthorizationInfo.addRole(role.getRoleCode());
            for (PermissionDTO permission : role.getPermissionSet()) {
                //2.1.1添加权限
                simpleAuthorizationInfo.addStringPermission(permission.getPermissionCode());
            }
        }
        return simpleAuthorizationInfo;
    }

    @Override
    /**
     * 认证 判断token的有效性
     *@param  [token]
     *@return org.apache.shiro.authc.AuthenticationInfo
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取token，既前端传入的token
        AuthToken accessToken = (AuthToken) token.getPrincipal();
        if(StringUtils.isBlank(accessToken.getSystemId())){
            throw new AuthenticationException("未知systemId，请重新登录");
        }
        //1. 根据accessToken，查询用户信息
        Token tokenEntity = tokenService.findByToken(accessToken.getToken());
        //2. token失效
        if (tokenEntity == null || tokenEntity.getExpireTime().isBefore(LocalDateTime.now())) {
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }
        //3. 调用数据库的方法, 从数据库中查询 userId 对应的未删除的用户记录
        User user = userService.findEffectiveUser(tokenEntity.getUserId());
        //4. 若用户不存在, 则可以抛出 UnknownAccountException 异常
        if (user == null) {
            throw new UnknownAccountException("用户不存在!");
        }
        //5. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(new UserDTO(user.getUserId(),null,accessToken.getSystemId(),null), accessToken, this.getName());
        return info;
    }
}
