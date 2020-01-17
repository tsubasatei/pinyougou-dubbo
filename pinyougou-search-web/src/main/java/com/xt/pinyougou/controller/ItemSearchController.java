package com.xt.pinyougou.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xt.pinyougou.service.ItemSearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(value = "item", description = "品牌管理-消费端")
@RestController
@RequestMapping("/consumer/item")
public class ItemSearchController {

    @Reference
    private ItemSearchService itemSearchService;

    @PostMapping("/search")
    public Map<String, Object> search(@ApiParam(value = "搜索条件", required = true) @RequestBody Map searchMap) {
        return itemSearchService.search(searchMap);
    }
}
