package com.hf.ciker.user.shiro;


import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * token类
 */
public class AuthToken extends UsernamePasswordToken {

    private String token;

    private String systemId;

    public AuthToken(String token,String systemId) {
        this.token = token;
        this.systemId = systemId;
    }

    public String getToken() {
        return token;
    }

    public String getSystemId() {
        return systemId;
    }

    @Override
    public Object getPrincipal() {
        return this;
    }

    @Override
    public Object getCredentials() {
        return this;
    }
}
