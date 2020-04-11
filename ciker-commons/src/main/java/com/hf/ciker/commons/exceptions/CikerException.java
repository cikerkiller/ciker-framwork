package com.hf.ciker.commons.exceptions;

/**
 * @dasc 框架异常
 * @author: ciker
 * @date: 2020/4/11
 */
public class CikerException extends BaseException {

    public CikerException(String msg, Object... params) {
        super(msg, params);
    }

    public CikerException(String msg, Throwable e, Object... params) {
        super(msg, e, params);
    }

    public CikerException(String msg) {
        super(msg);
    }

    public CikerException(String msg, Throwable e) {
        super(msg, e);
    }
}
