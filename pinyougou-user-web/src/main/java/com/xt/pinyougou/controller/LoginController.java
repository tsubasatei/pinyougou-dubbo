package com.xt.pinyougou.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取登录用户名
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @GetMapping("/name")
    public Map showName(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();//得到登录人账号
        Map map = new HashMap<>();
        map.put("loginName", name);
        return map;
    }
}

