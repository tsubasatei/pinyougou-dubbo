package com.xt.pinyougou.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xt.bean.Result;
import com.xt.pinyougou.entity.Seller;
import com.xt.pinyougou.service.SellerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  商家 前端控制器
 * </p>
 *
 * @author xt
 * @since 2020-01-07
 */
@RestController
@RequestMapping("/consumer/seller")
@Api(value = "seller", description = "商家管理-消费端")
public class SellerController {

    @Reference
    private SellerService sellerService;

    @ApiOperation(value = "查询商家详细信息", notes = "品牌详情")
    @GetMapping("/{sellerId}")
    public Seller get(@ApiParam(value = "商家ID", required = true) @PathVariable("sellerId") String sellerId) {
        return sellerService.getById(sellerId);
    }

    @ApiOperation(value = "获得商家分页列表", notes = "列表信息")
    @PostMapping("/page")
    public IPage<Seller> page(@ApiParam(value = "当前页码", required = true) Integer currentPage,
                             @ApiParam(value = "每页大小", required = true) Integer pageNum,
                             @RequestBody Seller seller) {

        return sellerService.selectPage(currentPage, pageNum, seller);
    }

    @ApiOperation(value = "更改商家状态", notes = "更改商家状态")
    @GetMapping("/updateStatus/{sellerId}/{status}")
    public Result updateStatus(@ApiParam(value = "商家ID", required = true) @PathVariable("sellerId") String sellerId,
                               @ApiParam(value = "商家状态", required = true) @PathVariable("status") String status) {
        Result result = new Result();
        try {
            boolean flag = sellerService.updateStatus(sellerId, status);
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
}

