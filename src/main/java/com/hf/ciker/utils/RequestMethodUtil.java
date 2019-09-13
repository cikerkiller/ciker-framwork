package com.hf.ciker.utils;

import com.hf.ciker.core.RequestMethod;
import org.apache.commons.lang3.StringUtils;

public class RequestMethodUtil {
    public static RequestMethod getRequestMethod(String requestMethod){
        if(StringUtils.equalsIgnoreCase("GET",requestMethod)){
            return RequestMethod.GET;
        }else if(StringUtils.equalsIgnoreCase("POST",requestMethod)){
            return RequestMethod.POST;
        }else if(StringUtils.equalsIgnoreCase("DELETE",requestMethod)){
            return RequestMethod.DELETE;
        }else if(StringUtils.equalsIgnoreCase("HEAD",requestMethod)){
            return RequestMethod.HEAD;
        }else if(StringUtils.equalsIgnoreCase("DELETE",requestMethod)){
            return RequestMethod.DELETE;
        }else if(StringUtils.equalsIgnoreCase("OPTIONS",requestMethod)){
            return RequestMethod.OPTIONS;
        }else if(StringUtils.equalsIgnoreCase("PUT",requestMethod)){
            return RequestMethod.PUT;
        }else if(StringUtils.equalsIgnoreCase("TRACE",requestMethod)) {
            return RequestMethod.TRACE;
        }else{
            throw  new RuntimeException("not found requstMethod : "+requestMethod);
        }

    }
}
