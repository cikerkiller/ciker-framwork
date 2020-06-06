package com.hf.ciker.user.controller;

import com.hf.ciker.user.dto.LoginDTO;
import com.hf.ciker.user.dto.ResponseCode;
import com.hf.ciker.user.dto.ServerResponse;
import com.hf.ciker.user.po.User;
import com.hf.ciker.user.service.TokenService;
import com.hf.ciker.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author ciker
 * @version 1.0
 * @date 2020/6/6 13:05
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @GetMapping("/test")
    public @ResponseBody ServerResponse getUserInfo(){
        return ServerResponse.createBySuccess("哈哈哈哈");
    }

    @PostMapping("/login")
    public @ResponseBody ServerResponse login(@RequestBody @Valid LoginDTO loginDTO,@RequestHeader("systemId")String systemId, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.BAD_REQUEST);
        }
        if(StringUtils.isBlank(systemId)){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.UNKNOWN);
        }
        User user = userService.findEffectiveUser(loginDTO.getUsername());
        if(user == null || !StringUtils.equals(user.getPassword(), loginDTO.getPassword())){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.BAD_REQUEST.getCode(),"账号或密码有误");
        }
        return ServerResponse.createBySuccess(tokenService.createToken(user.getUserId()));
    }

    @GetMapping("/logout")
    public @ResponseBody ServerResponse logout(@RequestHeader("token")String token){
        userService.logout(token);
        return ServerResponse.createBySuccess();
    }

}
