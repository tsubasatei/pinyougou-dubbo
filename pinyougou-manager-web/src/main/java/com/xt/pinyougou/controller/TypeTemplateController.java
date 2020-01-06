package com.xt.pinyougou.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xt.bean.Result;
import com.xt.pinyougou.entity.TypeTemplate;
import com.xt.pinyougou.service.TypeTemplateService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  类型模板 前端控制器
 * </p>
 *
 * @author xt
 * @since 2020-01-06
 */
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

    @ApiOperation(value = "获得类型模板列表", notes = "列表信息")
    @GetMapping("/list")
    public List<TypeTemplate> list() {
        return typeTemplateService.list();
    }

    @ApiOperation(value = "添加类型模板", notes = "添加类型模板")
    @PostMapping
    public Result add(@RequestBody TypeTemplate typeTemplate) {
        Result result = new Result();
        try {
            boolean flag = typeTemplateService.save(typeTemplate);
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

    @ApiOperation(value = "更新类型模板", notes = "更新类型模板")
    @PutMapping
    public Result update(@RequestBody TypeTemplate typeTemplate) {
        Result result = new Result();
        try {
            boolean flag = typeTemplateService.updateById(typeTemplate);
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

    @ApiOperation(value = "删除类型模板", notes = "删除类型模板")
    @DeleteMapping("/{id}")
    public Result delete(@ApiParam(value = "类型模板ID", required = true) @PathVariable("id") Long id) {
        Result result = new Result();
        try {
            boolean flag = typeTemplateService.removeById(id);
            result.setSuccess(flag);
            if (flag) {
                result.setMessage("删除成功");
            } else {
                result.setMessage("删除失败");
            }
        } catch (Exception e) {
            result = new Result(false, e.getMessage());
        }
        return result;
    }

    @ApiOperation(value = "批量删除类型模板", notes = "批量删除类型模板")
    @DeleteMapping("/deleteBatch/{ids}")
    public Result deleteBatch(@ApiParam(value = "类型模板IDs", required = true) @PathVariable("ids") Long[] ids) {
        Result result = new Result();
        try {
            boolean flag = typeTemplateService.removeByIds(Arrays.asList(ids));
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

    /**
     * Mybatis-Plus3 的 QueryWrapper 不支持 dubbo 序列化
     * @param currentPage
     * @param pageNum
     * @param typeTemplate
     * @return
     */
    @ApiOperation(value = "获得类型模板分页列表", notes = "列表信息")
    @PostMapping("/page")
    public IPage<TypeTemplate> page(@ApiParam(value = "当前页码", required = true) Integer currentPage,
                             @ApiParam(value = "每页大小", required = true) Integer pageNum,
                             @RequestBody TypeTemplate typeTemplate) {

        return typeTemplateService.selectPage(currentPage, pageNum, typeTemplate);
    }
}

