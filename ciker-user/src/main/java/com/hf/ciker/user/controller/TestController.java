package com.hf.ciker.user.controller;

import com.hf.ciker.user.dto.ServerResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ciker
 * @version 1.0
 * @date 2020/6/6 20:24
 */
@RestController
@RequestMapping("/test")
public class TestController {


    @GetMapping("/1")
    @RequiresPermissions({"select"})
    public ServerResponse test1(){
        return ServerResponse.createBySuccess();
    }
}
