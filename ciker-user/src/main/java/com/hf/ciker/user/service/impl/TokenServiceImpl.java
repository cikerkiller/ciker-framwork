package com.hf.ciker.user.service.impl;

import com.hf.ciker.user.dao.TokenMapper;
import com.hf.ciker.user.po.Token;
import com.hf.ciker.user.service.TokenService;
import com.hf.ciker.user.shiro.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author ciker
 * @version 1.0
 * @date 2020/6/6 15:47
 */
@Service
public class TokenServiceImpl implements TokenService {
    //1小时后失效
    private final static int EXPIRE = 1;

    @Autowired
    private TokenMapper tokenMapper;

    @Override
    public Token findByToken(String accessToken) {
        return tokenMapper.findByToken(accessToken);
    }

    @Override
    public Token createToken(String userId) {
        //生成一个token
        String token = TokenGenerator.generateValue();
        //当前时间
        LocalDateTime now = LocalDateTime.now();
        //过期时间
        LocalDateTime expireTime = now.plusHours(EXPIRE);
        //判断是否生成过token
        Token tokenEntity = tokenMapper.findByUserId(userId);
        if (tokenEntity == null) {
            tokenEntity = new Token();
            tokenEntity.setUserId(userId);
            //保存token
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);
            tokenMapper.insertToken(tokenEntity);
        } else {
            //更新token
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);
            tokenMapper.updateToken(tokenEntity);
        }
        return tokenEntity;
    }

    @Override
    public void loseEfficacyToken(String token) {
        Token byToken = findByToken(token);
        //生成一个token
        token = TokenGenerator.generateValue();
        //修改token
        byToken.setToken(token);
        //使前端获取到的token失效
        tokenMapper.updateToken(byToken);
    }
}
