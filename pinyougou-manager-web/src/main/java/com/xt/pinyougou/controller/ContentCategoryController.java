package com.xt.pinyougou.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xt.bean.Result;
import com.xt.pinyougou.entity.ContentCategory;
import com.xt.pinyougou.service.ContentCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 广告内容分类 前端控制器
 * </p>
 *
 * @author xt
 * @since 2020-01-11
 */
@Api(value = "contentCategory", description = "广告分类管理-消费端")
@RestController
@RequestMapping("/consumer/contentCategory")
public class ContentCategoryController {

    @Reference
    private ContentCategoryService contentCategoryService;

    @ApiOperation(value = "查询广告分类详细信息", notes = "广告分类详情")
    @GetMapping("/{id}")
    public ContentCategory get(@ApiParam(value = "广告分类ID", required = true) @PathVariable("id") Long id) {
        return contentCategoryService.getById(id);
    }

    @ApiOperation(value = "获得广告分类列表", notes = "列表信息")
    @GetMapping("/list")
    public List<ContentCategory> list() {
        return contentCategoryService.list();
    }

    @ApiOperation(value = "添加广告分类", notes = "添加广告分类")
    @PostMapping
    public Result add(@RequestBody ContentCategory ContentCategory) {
        Result result = new Result();
        try {
            boolean flag = contentCategoryService.save(ContentCategory);
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

    @ApiOperation(value = "更新广告分类", notes = "更新广告分类")
    @PutMapping
    public Result update(@RequestBody ContentCategory ContentCategory) {
        Result result = new Result();
        try {
            boolean flag = contentCategoryService.updateById(ContentCategory);
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

    @ApiOperation(value = "批量删除广告分类", notes = "批量删除广告分类")
    @DeleteMapping("/deleteBatch/{ids}")
    public Result deleteBatch(@ApiParam(value = "广告分类IDs", required = true) @PathVariable("ids") Long[] ids) {
        Result result = new Result();
        try {
            boolean flag = contentCategoryService.removeByIds(Arrays.asList(ids));
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

    @ApiOperation(value = "获得广告分类分页列表", notes = "列表信息")
    @PostMapping("/page")
    public IPage<ContentCategory> page(@ApiParam(value = "当前页码", required = true) Integer currentPage,
                             @ApiParam(value = "每页大小", required = true) Integer pageNum,
                             @RequestBody ContentCategory ContentCategory) {

        return contentCategoryService.selectPage(currentPage, pageNum, ContentCategory);
    }
}

