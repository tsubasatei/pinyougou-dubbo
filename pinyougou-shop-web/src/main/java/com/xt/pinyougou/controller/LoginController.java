package com.xt.pinyougou.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录验证
 */
@RestController
@Api(value = "login", description = "登录-消费端")
public class LoginController {

    /**
     * 获取当前登录用户信息
     */
    @ApiOperation(value = "获取当前登录用户信息", notes = "登录")
    @GetMapping("/getLoginName")
    public Map getLoginName() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String, Object> map = new HashMap<>();
        map.put("loginName", name);
        return map;
    }
}
