package com.hf.ciker.web.helper;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.hf.ciker.commons.utils.LogUtil;
import com.hf.ciker.core.aop.annotations.Aspect;
import com.hf.ciker.core.aop.core.AspectProxy;
import com.hf.ciker.core.aop.core.Proxy;
import com.hf.ciker.core.aop.core.ProxyManager;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @dasc
 * @author: ciker
 * @date: 2020/4/16
 */
public final class AopHelper {

    static {
        Map<Class<?>, Set<Class<?>>> proxyMap = createProxyMap();
        Map<Class<?>, List<Proxy>> targetMap = createTargetMap(proxyMap);
        targetMap.forEach((targetClass, proxyList) ->{
            Object proxy = ProxyManager.createProxy(targetClass, proxyList);
            BeanHelper.setBean(targetClass, proxy);
        });
    }

    private static Set<Class<?>> createTargetClassSet(Aspect aspect) {
        Set<Class<?>> targetClassSet = Sets.newHashSet();
        Class<? extends Annotation> annotation = aspect.value();
        if(annotation != null && !annotation.equals(Aspect.class)){
            targetClassSet.addAll(ClassHelper.getClassSetByAnnotation(annotation));
        }
        return targetClassSet;
    }

    private static Map<Class<?>, Set<Class<?>>> createProxyMap(){
        Map<Class<?>, Set<Class<?>>> proxyMap = Maps.newHashMap();
        Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySuper(AspectProxy.class);
        proxyClassSet.forEach( proxyClass ->{
            if(proxyClass.isAnnotationPresent(Aspect.class)){
                Aspect aspect = proxyClass.getAnnotation(Aspect.class);
                Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
                proxyMap.put(proxyClass,targetClassSet);
            }
        });
        return proxyMap;
    }

    private static Map<Class<?>, List<Proxy>> createTargetMap(Map<Class<?>, Set<Class<?>>> proxyMap){
        Map<Class<?>, List<Proxy>> targetMap = Maps.newHashMap();
        proxyMap.forEach((proxyClass,targetClassSet) ->{
            targetClassSet.forEach(targetClass ->{
                try {
                    Proxy proxy = (Proxy)proxyClass.newInstance();
                    if(targetMap.containsKey(targetClass)){
                        targetMap.get(targetClass).add(proxy);
                    }else{
                        List<Proxy> proxyList = Lists.newArrayList();
                        proxyList.add(proxy);
                        targetMap.put(targetClass, proxyList);
                    }
                } catch (Exception e) {
                    LogUtil.error("{} 实例化异常",proxyClass.getName(), e);
                }
            });
        });
        return targetMap;
    }
}
