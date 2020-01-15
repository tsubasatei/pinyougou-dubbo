package com.xt.pinyougou.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xt.bean.Result;
import com.xt.pinyougou.entity.Content;
import com.xt.pinyougou.service.ContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xt
 * @since 2020-01-11
 */
@RestController
@RequestMapping("/consumer/content")
@Api(value = "content", description = "广告管理-消费端")
public class ContentController {

    @Reference
    private ContentService contentyService;

    @ApiOperation(value = "查询广告详细信息", notes = "广告详情")
    @GetMapping("/{id}")
    public Content get(@ApiParam(value = "广告ID", required = true) @PathVariable("id") Long id) {
        return contentyService.getById(id);
    }

    @ApiOperation(value = "获得广告列表", notes = "列表信息")
    @GetMapping("/list")
    public List<Content> list() {
        return contentyService.list();
    }

    @ApiOperation(value = "添加广告", notes = "添加广告")
    @PostMapping
    public Result add(@RequestBody Content Content) {
        Result result = new Result();
        try {
            boolean flag = contentyService.insert(Content);
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

    @ApiOperation(value = "更新广告", notes = "更新广告")
    @PutMapping
    public Result update(@RequestBody Content Content) {
        Result result = new Result();
        try {
            boolean flag = contentyService.update(Content);
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

    @ApiOperation(value = "批量删除广告", notes = "批量删除广告")
    @DeleteMapping("/deleteBatch/{ids}")
    public Result deleteBatch(@ApiParam(value = "广告IDs", required = true) @PathVariable("ids") Long[] ids) {
        Result result = new Result();
        try {
            boolean flag = contentyService.deleteBatch(Arrays.asList(ids));
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

    @ApiOperation(value = "获得广告分页列表", notes = "列表信息")
    @PostMapping("/page")
    public IPage<Content> page(@ApiParam(value = "当前页码", required = true) Integer currentPage,
                                       @ApiParam(value = "每页大小", required = true) Integer pageNum,
                                       @RequestBody Content Content) {

        return contentyService.selectPage(currentPage, pageNum, Content);
    }
}

