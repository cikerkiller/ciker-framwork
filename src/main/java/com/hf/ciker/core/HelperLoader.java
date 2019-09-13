package com.hf.ciker.core;

import com.hf.ciker.helper.BeanHelper;
import com.hf.ciker.helper.ClassHelper;
import com.hf.ciker.helper.ControllerHelper;
import com.hf.ciker.helper.IocHelper;
import com.hf.ciker.utils.ClassUtil;

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
