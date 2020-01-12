package com.xt.pinyougou.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 跳转页面
 */
@Controller
@Api(value = "page", description = "跳转页面")
public class PageController {

    @ApiOperation(value = "主页", notes = "主页")
    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @ApiOperation(value = "合作招商", notes = "合作招商")
    @GetMapping("/cooperation")
    public String cooperation() {
        return "cooperation";
    }

    @ApiOperation(value = "商家后台", notes = "商家后台")
    @GetMapping("/shoplogin")
    public String shoplogin() {
        return "shoplogin";
    }

    @ApiOperation(value = "秒杀", notes = "秒杀")
    @GetMapping("/seckillIndex")
    public String seckillIndex() {
        return "seckill-index";
    }


}
