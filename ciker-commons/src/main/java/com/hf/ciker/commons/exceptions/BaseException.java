package com.hf.ciker.commons.exceptions;

import java.text.MessageFormat;

/**
 * @dasc 异常基类
 * @author: ciker
 * @date: 2020/4/11
 */
public class BaseException extends Exception {

    public BaseException(String msg, Object... params){
       super(MessageFormat.format(msg, params));
    }

    public BaseException(String msg, Throwable e, Object... params){
       super(MessageFormat.format(msg, params), e);
    }

    public BaseException(String msg){
        super(msg);
    }

    public BaseException(String msg, Throwable e){
        super(msg, e);
    }
}
