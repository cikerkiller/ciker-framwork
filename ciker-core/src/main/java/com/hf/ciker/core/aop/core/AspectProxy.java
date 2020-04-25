package com.hf.ciker.core.aop.core;

import com.hf.ciker.commons.exceptions.CikerException;

import java.lang.reflect.Method;

/**
 * @dasc
 * @author: ciker
 * @date: 2020/4/16
 */
public abstract class AspectProxy implements Proxy {

    @Override
    public final Object doProxy(ProxyChain proxyChain) throws CikerException {
        Object result;
        Class<?> targetClass = proxyChain.getTargetClass();
        Method targetMethod = proxyChain.getTargetMethod();
        Object[] methodParams = proxyChain.getMethodParams();
        begin();
        try{
            if(intercept(targetClass, targetMethod, methodParams)){
                before(targetClass, targetMethod, methodParams);
                result = proxyChain.doProxyChain();
                after(targetClass, targetMethod, methodParams);
                return result;
            }
            return proxyChain.doProxyChain();
        }catch (Throwable e){
            error(targetClass, targetMethod, methodParams, e);
            throw new CikerException("AspectProxy.doProxy error,{}",e.getMessage());
        }finally {
            end();
        }
    }
    public void begin(){}

    public boolean intercept(Class<?> targetClass, Method targetMethod, Object[] methodParams) throws Throwable{
        return true;
    }
    public void before(Class<?> targetClass, Method targetMethod, Object[] methodParams) throws Throwable{
    }
    public void after(Class<?> targetClass, Method targetMethod, Object[] methodParams) throws Throwable{
    }
    public void error(Class<?> targetClass, Method targetMethod, Object[] methodParams, Throwable e){
    }
    public void end(){}
}
