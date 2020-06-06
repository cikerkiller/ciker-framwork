package com.hf.ciker.user.dto;


/**
 * 响应码
 */
public enum ResponseCode {

    SUCCESS("000000","成功"),

    INSUFFICIENT_PERMISSIONS("999995","权限不足"),

    UNKNOWN("999996","未知systemId"),

    BAD_REQUEST("999997","请求错误"),

    NEED_LOGIN("999998","需要登录"),

    ERROR("999999","错误"),
    ;

    private final String code;

    private final String desc;


    ResponseCode(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public String getCode(){
        return code;
    }
    public String getDesc(){
        return desc;
    }

}
