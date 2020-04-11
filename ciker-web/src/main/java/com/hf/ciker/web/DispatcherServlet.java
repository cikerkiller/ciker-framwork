package com.hf.ciker.web;

import com.hf.ciker.commons.utils.*;
import com.hf.ciker.web.beans.Handler;
import com.hf.ciker.web.beans.Param;
import com.hf.ciker.web.beans.ServerResponse;
import com.hf.ciker.web.beans.View;
import com.hf.ciker.web.helper.BeanHelper;
import com.hf.ciker.web.helper.ConfigHelper;
import com.hf.ciker.web.helper.ControllerHelper;
import com.hf.ciker.web.utils.RequestMethodUtil;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

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
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestMethod requestMethod = RequestMethodUtil.getRequestMethod(req.getMethod());
        String path = req.getPathInfo();
        LogUtil.info("请求路径：path:{}",path);
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
            String body = CodecUtil.decodeURL(StreamUtil.getDataFromStream(req.getInputStream()));
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
            }else if(result instanceof ServerResponse){
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                PrintWriter writer = resp.getWriter();
                String json = JsonUtil.toJson(result);
                writer.write(json);
                writer.flush();
                writer.close();
            }else{
                throw new RuntimeException("result typr  not found ");
            }
        }
    }


    @Override
    public void init(ServletConfig config) throws ServletException {
        LogUtil.info("<<<======================项目初始化开始======================>>>");
        HelperLoader.init();
        ServletContext servletContext = config.getServletContext();
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath()+"*");
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath()+"*");
    }

    @Override
    public void destroy() {
        LogUtil.info("<<<======================项目停止运行======================>>>");
        super.destroy();
    }
}
