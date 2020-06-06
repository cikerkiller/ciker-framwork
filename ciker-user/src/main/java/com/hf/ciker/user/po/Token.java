package com.hf.ciker.user.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * token实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token implements Serializable {

    /**
     * 用户名
     */
    private String userId;

    /**
     * token
     */
    private String token;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
