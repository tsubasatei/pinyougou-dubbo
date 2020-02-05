package com.xt.pinyougou.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.xt.bean.Result;
import com.xt.pinyougou.pojo.Cart;
import com.xt.pinyougou.service.CartService;
import com.xt.pinyougou.util.CookieUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(value = "cart", description = "购物车管理-消费端")
@RestController
@RequestMapping("/consumer/cart")
public class CartController {

    @Reference
    private CartService cartService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;

    /**
     * 购物车列表
     */
    @ApiOperation(value = "获得购物车列表", notes = "列表信息")
    @GetMapping("/findCartList")
    public List<Cart> findCartList() {
        String cartListString = CookieUtil.getCookieValue(request, "cartList", "UTF-8");
        if (cartListString == null || cartListString.equals("")) {
           cartListString = "[]";
        }

        List<Cart> cartList_cookie = JSON.parseArray(cartListString, Cart.class);
        return cartList_cookie;
    }

    @ApiOperation(value = "添加商品到购物车", notes = "添加商品到购物车")
//    @PostMapping("/{itemId}/{num}")
    @GetMapping("/{itemId}/{num}")
    public Result addGoodsToCartList(@ApiParam(value = "商品SKU的ID", required = true) @PathVariable("itemId") Long itemId,
                                     @ApiParam(value = "数量", required = true) @PathVariable("num") Integer num) {
        try {
            List<Cart> cartList = findCartList();
            cartList = cartService.addGoodsToCartList(cartList, itemId, num);
            CookieUtil.setCookie(request, response, "cartList", JSON.toJSONString(cartList), 3600*24, "UTF-8");
            return new Result(true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "添加失败");
        }
    }

}
