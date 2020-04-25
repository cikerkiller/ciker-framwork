package com.hf.ciker.web.proxy;

import com.hf.ciker.commons.utils.LogUtil;
import com.hf.ciker.core.aop.annotations.Aspect;
import com.hf.ciker.core.aop.core.AspectProxy;
import com.hf.ciker.web.annatations.Controller;

import java.lang.reflect.Method;

/**
 * @dasc
 * @author: ciker
 * @date: 2020/4/16
 */
@Aspect(Controller.class)
public class ControllerProxy extends AspectProxy {

    private long begin;
    @Override
    public void before(Class<?> targetClass, Method targetMethod, Object[] methodParams) throws Throwable {
        LogUtil.info("--------------begin------------");
        LogUtil.info("class : {}, method : {}", targetClass.getName(), targetMethod.getName());
        begin = System.currentTimeMillis();
    }

    @Override
    public void after(Class<?> targetClass, Method targetMethod, Object[] methodParams) throws Throwable {
        LogUtil.info("execute time : {}", System.currentTimeMillis() - begin);
        LogUtil.info("--------------end------------");
    }
}
