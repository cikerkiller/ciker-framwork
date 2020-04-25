package com.hf.ciker.web;


import com.hf.ciker.commons.utils.ClassUtil;
import com.hf.ciker.web.helper.*;

public class HelperLoader {
    public static void init(){
        Class<?> []classList = {
                ClassHelper.class,
                BeanHelper.class,
                AopHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for(Class<?> cls : classList){
            ClassUtil.loadClass(cls.getName(),true);
        }
    }
}
