package com.hf.ciker.core;

import com.hf.ciker.bean.Data;
import com.hf.ciker.bean.Handler;
import com.hf.ciker.bean.Param;
import com.hf.ciker.bean.View;
import com.hf.ciker.helper.BeanHelper;
import com.hf.ciker.helper.ConfigHelper;
import com.hf.ciker.helper.ControllerHelper;
import com.hf.ciker.utils.*;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/*",loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(DispatcherServlet.class);
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestMethod requestMethod = RequestMethodUtil.getRequestMethod(req.getMethod());
        String path = req.getPathInfo();
        LOGGER.info("请求路径：path:{}",path);
        Handler handler = ControllerHelper.getHandler(requestMethod, path);
        if(handler != null){
            Class<?> controllerClass = handler.getControllerClass();
            Object controllerBean = BeanHelper.getBean(controllerClass);
            Map<String, Object> paramMap = new HashMap<>();
            Enumeration<String> parameterNames = req.getParameterNames();
            while (parameterNames.hasMoreElements()){
                String param = parameterNames.nextElement();
                paramMap.put(param,req.getParameter(param));
            }
            String body = CodecUtil.decodeURL(StreamUtil.getString(req.getInputStream()));
            if(StringUtils.isNotEmpty(body)){
                String[] params = StringUtils.split(body, "&");
                for(String p : params){
                    String[] array = StringUtils.split(p, "=");
                    if(ArrayUtils.isNotEmpty(array) && array.length == 2){
                        paramMap.put(array[0],array[1]);
                    }
                }
            }
            Method actionMethod = handler.getActionMethod();
            Object result = null;
            if(MapUtils.isNotEmpty(paramMap)){
                Param param = new Param(paramMap);
                result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
            }else{
                result = ReflectionUtil.invokeMethod(controllerBean, actionMethod);
            }
            if(result instanceof View){
                View view = (View) result;
                String viewPath = view.getPath();
                if(StringUtils.isNotEmpty(viewPath)){
                    if (StringUtils.startsWith(viewPath,"/")){
                        resp.sendRedirect(req.getContextPath()+viewPath);
                    }else{
                        Map<String, Object> model = view.getModel();
                        for (Map.Entry<String,Object> entry : model.entrySet()){
                            req.setAttribute(entry.getKey(),entry.getValue());
                        }
                        req.getRequestDispatcher(ConfigHelper.getAppJspPath()+viewPath).forward(req,resp);
                    }
                }
            }else if(result instanceof Data){
                Data data = (Data) result;
                Object model = data.getModel();
                if (model != null){
                    resp.setContentType("application/json");
                    resp.setCharacterEncoding("UTF-8");
                    PrintWriter writer = resp.getWriter();
                    String json = JsonUil.toJson(model);
                    writer.write(json);
                    writer.flush();
                    writer.close();
                }
            }else{
                throw new RuntimeException("result typr  not found ");
            }
        }
    }


    @Override
    public void init(ServletConfig config) throws ServletException {
        LOGGER.info("<<<======================项目初始化开始======================>>>");
        HelperLoader.init();
        ServletContext servletContext = config.getServletContext();
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath()+"*");
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath()+"*");
    }
}
