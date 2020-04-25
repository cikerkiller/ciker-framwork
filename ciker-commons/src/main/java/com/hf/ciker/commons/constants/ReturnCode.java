package com.hf.ciker.commons.constants;

import org.apache.commons.lang3.StringUtils;

public enum ReturnCode {
    SUCCESS("000000","成功"),
    INVALID_PARAM("100000","字段校验非法"),
    INVALID_REQUEST_DATA("200000","请求数据非法");
    private String code;
    private String msg;
    ReturnCode(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static ReturnCode getEnumByCode(String code){
        for(ReturnCode returnCode : ReturnCode.values()){
            if(StringUtils.equals(returnCode.getCode(),code)){
                return returnCode;
            }
        }
        return null;
    }
}