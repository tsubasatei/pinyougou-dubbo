package com.xt.pinyougou.controller;

import com.xt.pinyougou.entity.Content;
import com.xt.pinyougou.service.ContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "content", description = "广告管理-消费端")
@RestController
@RequestMapping("/content")
public class ContentController {
    @Autowired
    private ContentService contentService;

    @ApiOperation(value = "查询广告详细信息", notes = "广告详情")
    @GetMapping("/{id}")
    public Content get(@ApiParam(value = "广告ID", required = true) @PathVariable("id") Long id) {
        return contentService.getById(id);
    }
}
