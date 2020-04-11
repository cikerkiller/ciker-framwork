package com.hf.ciker.commons.exceptions;

/**
 * @dasc 框架运行异常
 * @author: ciker
 * @date: 2020/4/11
 */
public class CikerRuntimeException extends BaseRuntimeException {

    public CikerRuntimeException(String msg, Object... params) {
        super(msg, params);
    }

    public CikerRuntimeException(String msg, Throwable e, Object... params) {
        super(msg, e, params);
    }

    public CikerRuntimeException(String msg) {
        super(msg);
    }

    public CikerRuntimeException(String msg, Throwable e) {
        super(msg, e);
    }
}
