package com.hf.ciker.commons.utils;

import com.hf.ciker.commons.exceptions.CikerRuntimeException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtil {

    public static Object newInstance(Class<?> cls){
        try {
            return cls.newInstance();
        }catch (Exception e){
            LogUtil.error("new instance error",e);
            throw new CikerRuntimeException("new instance error", e);
        }
    }

    public static Object invokeMethod(Object obj, Method method,Object...objs){
        try {
            method.setAccessible(true);
            return method.invoke(obj,objs);
        } catch (Exception e){
            LogUtil.error("invokeMethod error",e);
            throw new CikerRuntimeException("invokeMethod error",e);
        }
    }

    public static void setField(Object obj, Field field, Object value){
        try {
            field.setAccessible(true);
            field.set(obj,value);
        } catch (IllegalAccessException e) {
            LogUtil.error("setField error",e);
            throw new RuntimeException(e);
        }
    }

}
