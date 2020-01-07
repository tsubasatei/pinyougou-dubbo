package com.xt.pinyougou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 跳转页面
 */
@Controller
public class PageController {

    // 注册
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    // 品优购商家入驻协议
    @GetMapping("/sampling")
    public String sampling() {
        return "sampling";
    }

    // 合作招商
    @GetMapping("/cooperation")
    public String cooperation() {
        return "cooperation";
    }

    // 首页
    @GetMapping("/index")
    public String index() {
        return "admin/index";
    }

    // 主页
    @GetMapping("/home")
    public String home() {
        return "admin/home";
    }
}
