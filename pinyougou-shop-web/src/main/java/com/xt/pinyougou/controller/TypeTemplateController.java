package com.xt.pinyougou.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xt.bean.Result;
import com.xt.pinyougou.entity.TypeTemplate;
import com.xt.pinyougou.service.TypeTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  类型模板 前端控制器
 * </p>
 *
 * @author xt
 * @since 2020-01-06
 */
@Api(value = "typeTemplate", description = "模板管理-消费端")
@RestController
@RequestMapping("/consumer/typeTemplate")
public class TypeTemplateController {

    @Reference
    private TypeTemplateService typeTemplateService;

    @ApiOperation(value = "查询类型模板详细信息", notes = "类型模板详情")
    @GetMapping("/{id}")
    public TypeTemplate get(@ApiParam(value = "类型模板ID", required = true) @PathVariable("id") Long id) {
        return typeTemplateService.getById(id);
    }


    @ApiOperation(value = "查询规格列表", notes = "查询规格列表")
    @GetMapping("/findSpecList/{id}")
    public List<Map> findSpecList(@ApiParam(value = "类型模板ID", required = true) @PathVariable Long id) {
        List<Map> list = typeTemplateService.findSpecList(id);
        return list;
    }
}

