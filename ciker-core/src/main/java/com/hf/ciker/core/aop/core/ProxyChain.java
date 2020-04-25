package com.hf.ciker.core.aop.core;

import com.hf.ciker.commons.exceptions.CikerException;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @dasc
 * @author: ciker
 * @date: 2020/4/16
 */
public class ProxyChain {
    private final Class<?> targetClass;
    private final Object targetObject;
    private final Method targetMethod;
    private final MethodProxy methodProxy;
    private final Object[] methodParams;

    private List<Proxy> proxyList;

    private int proxyIndex = 0;

    public ProxyChain(Class<?> targetClass, Object targetObject, Method targetMethod, MethodProxy methodProxy, Object[] methodParams, List<Proxy> proxyList) {
        this.targetClass = targetClass;
        this.targetObject = targetObject;
        this.targetMethod = targetMethod;
        this.methodProxy = methodProxy;
        this.methodParams = methodParams;
        this.proxyList = proxyList;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Method getTargetMethod() {
        return targetMethod;
    }

    public Object[] getMethodParams() {
        return methodParams;
    }

    public Object doProxyChain() throws CikerException{
        if(proxyIndex < proxyList.size()){
            return proxyList.get(proxyIndex++).doProxy(this);
        }
        try {
            return methodProxy.invokeSuper(targetObject, methodParams);
        } catch (Throwable throwable) {
            throw new CikerException("代理方法执行异常--> error msg :{}",throwable.getMessage());
        }
    }
}
