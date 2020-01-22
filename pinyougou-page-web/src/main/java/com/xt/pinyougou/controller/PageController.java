package com.xt.pinyougou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {

    @GetMapping("/{goodsId}")
    public String page(@PathVariable("goodsId") String goodsId) {
        int index = goodsId.indexOf(".html");
        goodsId = goodsId.substring(0, index);
        return goodsId;
    }
}
