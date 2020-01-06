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
    @GetMapping("/index")
    public String index() {
        return "admin/index";
    }

    // 品牌
    @GetMapping("/brand")
    public String brand() {
        return "admin/brand";
    }

    // 规格
    @GetMapping("/specification")
    public String specification() {
        return "admin/specification";
    }

    // 模板
    @GetMapping("/typeTemplate")
    public String typeTemplate() {
        return "admin/type_template";
    }

}
