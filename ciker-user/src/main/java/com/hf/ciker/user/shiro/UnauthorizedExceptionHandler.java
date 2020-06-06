package com.hf.ciker.user.shiro;

import com.hf.ciker.user.dto.ResponseCode;
import com.hf.ciker.user.dto.ServerResponse;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ciker
 * @version 1.0
 * @date 2020/6/6 23:49
 */
@ControllerAdvice
public class UnauthorizedExceptionHandler {

    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseBody
    public ServerResponse jsonExceptionHandler(HttpServletRequest req, Exception e) {
        return ServerResponse.createByErrorCodeMessage(ResponseCode.INSUFFICIENT_PERMISSIONS);
    }
}
