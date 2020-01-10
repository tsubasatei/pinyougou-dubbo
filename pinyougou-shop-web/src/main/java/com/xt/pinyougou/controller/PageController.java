package com.xt.pinyougou.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 跳转页面
 */
@Controller
public class PageController {

    // 注册
    @ApiOperation(value = "注册", notes = "注册")
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    // 品优购商家入驻协议
    @ApiOperation(value = "品优购商家入驻协议", notes = "品优购商家入驻协议")
    @GetMapping("/sampling")
    public String sampling() {
        return "sampling";
    }

    // 合作招商
    @ApiOperation(value = "合作招商页", notes = "合作招商")
    @GetMapping("/cooperation")
    public String cooperation() {
        return "cooperation";
    }

    // 首页
    @GetMapping("/index")
    @ApiOperation(value = "首页", notes = "首页")
    public String index() {
        return "admin/index";
    }

    // 主页
    @GetMapping("/home")
    @ApiOperation(value = "主页", notes = "主页")
    public String home() {
        return "admin/home";
    }

    @GetMapping("/goods")
    @ApiOperation(value = "商品管理", notes = "商品管理")
    public String goods() {
        return "admin/goods";
    }

    @GetMapping("/goodsEdit")
    @ApiOperation(value = "新增商品", notes = "新增商品")
    public String goodEdit() {
        return "admin/goods_edit";
    }
}
