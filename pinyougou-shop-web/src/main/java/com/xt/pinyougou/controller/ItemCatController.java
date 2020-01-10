package com.xt.pinyougou.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.xt.bean.Result;
import com.xt.pinyougou.entity.ItemCat;
import com.xt.pinyougou.service.ItemCatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 商品类目 前端控制器
 * </p>
 *
 * @author xt
 * @since 2020-01-07
 */
@Api(value = "itemCat", description = "商品分类管理-消费端")
@RestController
@RequestMapping("/consumer/itemCat")
public class ItemCatController {

    @Reference
    private ItemCatService itemCatService;

    @GetMapping("/findByParentId/{parentId}")
    @ApiOperation(value = "根据上级ID获取分类列表", notes = "列表信息")
    public List<ItemCat> findByParentId(@ApiParam(value = "父级ID", required = true) @PathVariable("parentId") Long parentId) {
        return itemCatService.findByParentId(parentId);
    }

    @ApiOperation(value = "查询商品分类详细信息", notes = "商品分类详情")
    @GetMapping("/{id}")
    public ItemCat get(@ApiParam(value = "商品分类ID", required = true) @PathVariable("id") Long id) {
        return itemCatService.getById(id);
    }

    @GetMapping("/list")
    @ApiOperation(value = "获取所有分类列表", notes = "列表信息")
    public List<ItemCat> findList() {
        return itemCatService.list();
    }
}

