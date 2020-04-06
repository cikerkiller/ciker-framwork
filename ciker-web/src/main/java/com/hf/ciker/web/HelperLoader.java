package com.hf.ciker.web;


import com.hf.ciker.commons.utils.ClassUtil;
import com.hf.ciker.web.helper.BeanHelper;
import com.hf.ciker.web.helper.ClassHelper;
import com.hf.ciker.web.helper.ControllerHelper;
import com.hf.ciker.web.helper.IocHelper;

public class HelperLoader {
    public static void init(){
        Class<?> []classList = {
                ClassHelper.class,
                BeanHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for(Class<?> cls : classList){
            ClassUtil.loadClass(cls.getName(),true);
        }
    }
}
