package com.hf.ciker.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);

    public static Object newInstance(Class<?> cls){
        Object instance = null;
        try {
            instance = cls.newInstance();
        }catch (Exception e){
            LOGGER.error("new instance error",e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    public static Object invokeMethod(Object obj, Method method,Object...objs){
        Object result = null;
        try {
            method.setAccessible(true);
            result = method.invoke(obj,objs);
        } catch (Exception e){
            LOGGER.error("invokeMethod error",e);
            throw new RuntimeException(e);
        }
        return result;
    }

    public static void setField(Object obj, Field field, Object value){
        try {
            field.setAccessible(true);
            field.set(obj,value);
        } catch (IllegalAccessException e) {
            LOGGER.error("setField error",e);
            throw new RuntimeException(e);
        }
    }

}
