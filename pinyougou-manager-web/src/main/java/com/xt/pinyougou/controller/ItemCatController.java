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

    @ApiOperation(value = "添加商品分类", notes = "添加商品分类")
    @PostMapping
    public Result add(@RequestBody ItemCat itemCat) {
        Result result = new Result();
        try {
            boolean flag = itemCatService.save(itemCat);
            result.setSuccess(flag);
            if (flag) {
                result.setMessage("添加成功");
            } else {
                result.setMessage("添加失败");
            }
        } catch (Exception e) {
            result = new Result(false, e.getMessage());
        }
        return result;
    }

    @ApiOperation(value = "更新商品分类", notes = "更新商品分类")
    @PutMapping
    public Result update(@RequestBody ItemCat itemCat) {
        Result result = new Result();
        try {
            boolean flag = itemCatService.updateById(itemCat);
            result.setSuccess(flag);
            if (flag) {
                result.setMessage("更新成功");
            } else {
                result.setMessage("更新失败");
            }
        } catch (Exception e) {
            result = new Result(false, e.getMessage());
        }
        return result;
    }

    @ApiOperation(value = "查询商品分类详细信息", notes = "商品分类详情")
    @GetMapping("/{id}")
    public ItemCat get(@ApiParam(value = "商品分类ID", required = true) @PathVariable("id") Long id) {
        return itemCatService.getById(id);
    }

    @ApiOperation(value = "批量删除商品分类", notes = "批量删除商品分类")
    @DeleteMapping("/deleteBatch/{ids}")
    public Result deleteBatch(@ApiParam(value = "商品分类IDs", required = true) @PathVariable("ids") Long[] ids) {
        Result result = new Result();
        try {
            boolean flag = itemCatService.deleteBatch(Arrays.asList(ids));
            result.setSuccess(flag);
            if (flag) {
                result.setMessage("批量删除成功");
            } else {
                result.setMessage("批量删除失败");
            }
        } catch (Exception e) {
            result = new Result(false, e.getMessage());
        }
        return result;
    }
}

