package com.xt.pinyougou.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.xt.bean.Result;
import com.xt.pinyougou.entity.Brand;
import com.xt.pinyougou.entity.Seller;
import com.xt.pinyougou.service.SellerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;

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

    @ApiOperation(value = "商家注册", notes = "添加商家")
    @PostMapping
    public Result add(@RequestBody Seller seller) {
        Result result = new Result();
        try {
            seller.setStatus("0");
            seller.setCreateTime(LocalDateTime.now());
            boolean flag = sellerService.save(seller);
            result.setSuccess(flag);
            if (flag) {
                result.setMessage("注册成功");
            } else {
                result.setMessage("注册失败");
            }
        } catch (Exception e) {
            result = new Result(false, e.getMessage());
        }
        return result;
    }
}

