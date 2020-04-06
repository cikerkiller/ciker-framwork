package com.hf.ciker.web.helper;

import com.hf.ciker.commons.utils.ClassUtil;
import com.hf.ciker.web.annatations.Controller;
import com.hf.ciker.web.annatations.Service;

import java.util.HashSet;
import java.util.Set;

public final class ClassHelper {
    private static final Set<Class<?>> CLASS_SET;
    static {
        String basepackage = ConfigHelper.getAppBasePackage();
        CLASS_SET = ClassUtil.getClassSet(basepackage);
    }

    public static Set<Class<?>> getClassSet(){
        return CLASS_SET;
    }
    public static Set<Class<?>> getServiceClassSet(){
        Set<Class<?>>  serviceClassSet = new HashSet<>();
        for(Class<?> clz : CLASS_SET){
            if(clz.isAnnotationPresent(Service.class)){
                serviceClassSet.add(clz);
            }
        }
        return serviceClassSet;
    }
    public static Set<Class<?>> getControllerClassSet(){
        Set<Class<?>>  controllerClassSet = new HashSet<>();
        for(Class<?> clz : CLASS_SET){
            if(clz.isAnnotationPresent(Controller.class)){
                controllerClassSet.add(clz);
            }
        }
        return controllerClassSet;
    }
    public static  Set<Class<?>> getBeanClassSet(){
        Set<Class<?>>  beanSet = new HashSet<>();
        beanSet.addAll(getServiceClassSet());
        beanSet.addAll(getControllerClassSet());
        return beanSet;
    }

}
