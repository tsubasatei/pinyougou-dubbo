package com.xt.pinyougou.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xt.bean.Result;
import com.xt.pinyougou.entity.Goods;
import com.xt.pinyougou.pojo.GoodsGroup;
import com.xt.pinyougou.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * <p>
 *  商品 前端控制器
 * </p>
 *
 * @author xt
 * @since 2020-01-07
 */
@Api(value = "goods", description = "商品管理-消费端")
@RestController
@RequestMapping("/consumer/goods")
public class GoodsController {

    @Reference
    private GoodsService goodsService;

    @ApiOperation(value = "查询商品详细信息", notes = "商品详情")
    @GetMapping("/{id}")
    public GoodsGroup get(@ApiParam(value = "商品ID", required = true) @PathVariable("id") Long id) {
        return goodsService.findOne(id);
    }
    @ApiOperation(value = "获得商品分页列表", notes = "列表信息")
    @PostMapping("/page")
    public IPage<Goods> page(@ApiParam(value = "当前页码", required = true) Integer currentPage,
                             @ApiParam(value = "每页大小", required = true) Integer pageNum,
                             @RequestBody Goods goods) {
        return goodsService.selectPage(currentPage, pageNum, goods);
    }

    @ApiOperation(value = "查询商品详细信息", notes = "商品详情")
    @GetMapping("/updateStatus/{ids}/{status}")
    public Result updateStatus(@ApiParam(value = "商品IDs", required = true) @PathVariable("ids") Long[] ids,
                               @ApiParam(value = "审核状态", required = true) @PathVariable("status") String status) {
        try {
            boolean flag = goodsService.updateStatus(Arrays.asList(ids), status);
            if (flag) {
                return new Result(true, "审批成功");
            } else {
                return new Result(false, "审批失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, e.getMessage());
        }
    }

    @ApiOperation(value = "批量删除商品", notes = "批量删除商品")
    @DeleteMapping("/deleteBatch/{ids}")
    public Result deleteBatch(@ApiParam(value = "商品IDs", required = true) @PathVariable("ids") Long[] ids) {
        Result result = new Result();
        try {
            boolean flag = goodsService.removeByIds(Arrays.asList(ids));
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

