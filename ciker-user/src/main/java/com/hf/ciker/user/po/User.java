package com.hf.ciker.user.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 用户表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class User extends BasePO{

    /**
     * ck_user 表主键，用户id
     */
    private String userId;

    /**
     * 密码
     */
    private String password;



}