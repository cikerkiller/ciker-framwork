package com.hf.ciker.core.aop.core;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.util.List;

/**
 * @dasc
 * @author: ciker
 * @date: 2020/4/16
 */
public class ProxyManager {

    public static <T> T createProxy(final Class<?> targetClass, final List<Proxy> proxyList){
        return (T) Enhancer.create(targetClass, (MethodInterceptor)
                (targetObject, targetMethod, methodParams, proxy) ->
                        new ProxyChain(targetClass, targetObject,
                                targetMethod, proxy, methodParams,proxyList));
    }

}
