package com.xt.pinyougou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 跳转页面
 */
@Controller
@RequestMapping("/admin")
public class PageController {

    // 首页
    @GetMapping("/index.html")
    public String index() {
        return "admin/index";
    }

    // 品牌
    @GetMapping("/brand.html")
    public String brand() {
        return "admin/brand";
    }

    // 品牌
    @GetMapping("/specification.html")
    public String specification() {
        return "admin/specification";
    }

}
