package com.hf.ciker.commons.exceptions;

import java.text.MessageFormat;

/**
 * @dasc 运行时异常基类
 * @author: ciker
 * @date: 2020/4/11
 */
public class BaseRuntimeException extends RuntimeException {

    public BaseRuntimeException(String msg, Object... params){
        super(MessageFormat.format(msg, params));
    }

    public BaseRuntimeException(String msg, Throwable e, Object... params){
        super(MessageFormat.format(msg, params), e);
    }

    public BaseRuntimeException(String msg){
        super(msg);
    }

    public BaseRuntimeException(String msg, Throwable e){
        super(msg, e);
    }
}
