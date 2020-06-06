package com.hf.ciker.user.dao;

import com.hf.ciker.user.po.Token;

/**
 * @author ciker
 * @version 1.0
 * @date 2020/6/6 13:32
 */
public interface TokenMapper {

    Token findByUserId(String userId);

    Token findByToken(String accessToken);

    void updateToken(Token token);

    void insertToken(Token token);
}
