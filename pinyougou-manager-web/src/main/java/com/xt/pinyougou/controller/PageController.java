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
@RequestMapping("/admin")
@Api(value = "page", description = "跳转页面")
public class PageController {

    // 首页
    @ApiOperation(value = "首页", notes = "首页")
    @GetMapping("/index")
    public String index() {
        return "admin/index";
    }

    // 品牌
    @ApiOperation(value = "品牌管理", notes = "品牌管理")
    @GetMapping("/brand")
    public String brand() {
        return "admin/brand";
    }

    // 规格
    @GetMapping("/specification")
    @ApiOperation(value = "规格管理", notes = "规格管理")
    public String specification() {
        return "admin/specification";
    }

    // 模板
    @GetMapping("/typeTemplate")
    @ApiOperation(value = "模板管理", notes = "模板管理")
    public String typeTemplate() {
        return "admin/type_template";
    }

    @GetMapping("/seller1")
    @ApiOperation(value = "商家审核", notes = "商家审核")
    public String seller1() {
        return "admin/seller_1";
    }

    @GetMapping("/seller")
    @ApiOperation(value = "商家管理", notes = "商家管理")
    public String seller() {
        return "admin/seller";
    }

    @GetMapping("/itemCat")
    @ApiOperation(value = "分类管理", notes = "分类管理")
    public String itemCat() {
        return "admin/item_cat";
    }

    @GetMapping("/goods")
    @ApiOperation(value = "商品审核", notes = "商品审核")
    public String goods() {
        return "admin/goods";
    }

    @GetMapping("/goodsDetail")
    @ApiOperation(value = "商品详情", notes = "商品详情")
    public String goodsDetail() {
        return "admin/goods_detail";
    }

}
