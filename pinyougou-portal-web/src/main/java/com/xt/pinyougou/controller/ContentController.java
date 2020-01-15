package com.xt.pinyougou.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.xt.pinyougou.entity.Content;
import com.xt.pinyougou.service.ContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 广告 前端控制器
 * </p>
 *
 * @author xt
 * @since 2020-01-11
 */
@Api(value = "content", description = "广告管理-消费端")
@RestController
@RequestMapping("/consumer/content")
public class ContentController {

    @Reference
    private ContentService contentService;

    @ApiOperation(value = "查询广告分类详细信息", notes = "广告分类详情")
    @GetMapping("/findContentList/{categoryId}")
    public List<Content> findContentList(@ApiParam(value = "广告分类ID", required = true) @PathVariable("categoryId") Long categoryId) {
        return contentService.findListByCategoryId(categoryId);
    }

}

