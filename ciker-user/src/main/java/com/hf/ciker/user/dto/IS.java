package com.hf.ciker.user.dto;

/**
 * @author ciker
 * @version 1.0
 * @date 2020/6/6 15:35
 */
public enum IS {

    Y("Y"),

    N("N");

    private String value;

    IS(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
