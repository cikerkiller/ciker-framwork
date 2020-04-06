package com.hf.ciker.web.helper;

import com.hf.ciker.web.Request;
import com.hf.ciker.web.RequestMethod;
import com.hf.ciker.web.annatations.RequestMapping;
import com.hf.ciker.web.beans.Handler;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ControllerHelper {
    private static final Map<Request, Handler> ACTION_MAP = new HashMap<>();
    static {
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if(CollectionUtils.isNotEmpty(controllerClassSet)){
            for (Class<?> controllerClass : controllerClassSet){
                Method[] methods = controllerClass.getDeclaredMethods();
                if (ArrayUtils.isNotEmpty(methods)){
                    for(Method method : methods){
                        if(method.isAnnotationPresent(RequestMapping.class)){
                            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                            RequestMethod requestMethod = requestMapping.method();
                            String requestPath = requestMapping.path();
                            Request request = new Request(requestMethod,requestPath);
                            Handler handler = new Handler(controllerClass,method);
                            ACTION_MAP.put(request,handler);
                        }
                    }
                }
            }
        }
    }
    public static Handler getHandler(RequestMethod requestMethod, String requestPath){
        return ACTION_MAP.get(new Request(requestMethod,requestPath));
    }
}
