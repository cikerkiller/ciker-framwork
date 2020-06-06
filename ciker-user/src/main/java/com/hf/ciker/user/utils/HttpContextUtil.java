package com.hf.ciker.user.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class HttpContextUtil {
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static String getDomain(){
        HttpServletRequest request = getHttpServletRequest();
        StringBuffer url = request.getRequestURL();
        return url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
    }

    public static String getOrigin(){
        HttpServletRequest request = getHttpServletRequest();
        return request.getHeader("Origin");
    }

    /**
     * 从请求头或者参数中获取指定参数名的值
     * @param httpRequest
     * @param param
     * @return
     */
    public static String getRequestHeader(HttpServletRequest httpRequest, String param) {
        String header = httpRequest.getHeader(param);
        if (StringUtils.isBlank(header)) {
            header = httpRequest.getParameter(param);
        }
        return header;
    }
}