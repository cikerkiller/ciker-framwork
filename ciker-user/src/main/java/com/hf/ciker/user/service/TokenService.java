package com.hf.ciker.user.service;

import com.hf.ciker.user.po.Token;

/**
 * @author ciker
 * @version 1.0
 * @date 2020/6/6 15:08
 */
public interface TokenService {

    /**
     * 根据访问token查找库中数据
     * @param accessToken
     * @return
     */
    Token findByToken(String accessToken);

    /**
     * 创建token（若库中没有）
     * @param userId
     * @return
     */
    Token createToken(String userId);

    /**
     * 使token失效
     * @param token
     */
    void loseEfficacyToken(String token);
}
