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
import org.springframework.security.core.context.SecurityContextHolder;
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

    @ApiOperation(value = "添加商品", notes = "添加商品")
    @PostMapping
    public Result add(@RequestBody GoodsGroup goodsGroup) {
        Result result = new Result();
        try {
            // 当前用户
            String sellId = SecurityContextHolder.getContext().getAuthentication().getName();
            goodsGroup.getGoods().setSellerId(sellId);
            goodsGroup.getGoods().setAuditStatus("0"); // 设置未申请状态
            goodsGroup.getGoods().setIsDelete("0"); // 是否删除
            goodsGroup.getGoods().setIsMarketable("0"); // 上/下架
            boolean flag = goodsService.add(goodsGroup);
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

    @ApiOperation(value = "查询商品详细信息", notes = "商品详情")
    @GetMapping("/{id}")
    public GoodsGroup get(@ApiParam(value = "商品ID", required = true) @PathVariable("id") Long id) {
        return goodsService.findOne(id);
    }

    @ApiOperation(value = "更新商品", notes = "更新商品")
    @PutMapping
    public Result update(@RequestBody GoodsGroup goodsGroup) {
        Result result = new Result();
        try {
            // 校验是否是当前商家的id
            String sellerId = goodsService.getById(goodsGroup.getGoods().getId()).getSellerId();

            // 获取当前登录的商家ID
            String loginName = SecurityContextHolder.getContext().getAuthentication().getName();

            // 如果传递过来的商家ID并不是当前登录的用户的ID,则属于非法操作
            if (!loginName.equals(sellerId) || !goodsGroup.getGoods().getSellerId().equals(loginName)) {
                return new Result(false, "非法操作");
            }
            goodsGroup.getGoods().setAuditStatus("0");
            goodsGroup.getGoods().setIsDelete("0"); // 是否删除
            goodsGroup.getGoods().setIsMarketable("0");
            boolean flag = goodsService.updateGroup(goodsGroup);
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

    @ApiOperation(value = "上/下架", notes = "上/下架")
    @GetMapping("/updateMarketable/{id}/{marketable}")
    public Result updateMarketable(@ApiParam(value = "商品ID", required = true) @PathVariable("id") Long id,
                                   @ApiParam(value = "是否上架", required = true) @PathVariable("marketable") String marketable) {
        try {
            boolean flag = goodsService.updateMarketable(id, marketable);
            if (flag && "0".equals(marketable)) {
                return new Result(true, "上架成功");
            }
            if (flag && "1".equals(marketable)) {
                return new Result(true, "下架成功");
            }
            return new Result(false, "上/下架失败");
        } catch (Exception e) {
            return new Result(false, e.getMessage());
        }
    }

    @ApiOperation(value = "获得商品分页列表", notes = "列表信息")
    @PostMapping("/page")
    public IPage<Goods> page(@ApiParam(value = "当前页码", required = true) Integer currentPage,
                             @ApiParam(value = "每页大小", required = true) Integer pageNum,
                             @RequestBody Goods goods) {
        String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
        goods.setSellerId(sellerId);
        return goodsService.selectPage(currentPage, pageNum, goods);
    }
}

