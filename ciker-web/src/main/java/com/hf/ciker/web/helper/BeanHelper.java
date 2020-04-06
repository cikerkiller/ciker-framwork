package com.hf.ciker.web.helper;


import com.hf.ciker.commons.utils.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class BeanHelper {
    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<>();
    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        for(Class<?> beanClass : beanClassSet){
            Object instance = ReflectionUtil.newInstance(beanClass);
            BEAN_MAP.put(beanClass,instance);
        }
    }
    public static Map<Class<?>,Object> getBeanMap(){
        return BEAN_MAP;
    }

    public static <T> T getBean(Class<T> cls){
        if(!BEAN_MAP.containsKey(cls)){
            throw new RuntimeException("not found class: "+cls);
        }
        return (T) BEAN_MAP.get(cls);
    }
}
