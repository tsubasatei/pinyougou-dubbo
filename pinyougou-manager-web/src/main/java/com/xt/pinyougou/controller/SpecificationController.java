package com.xt.pinyougou.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xt.bean.Result;
import com.xt.pinyougou.entity.Specification;
import com.xt.pinyougou.pojo.SpecificationGroup;
import com.xt.pinyougou.service.SpecificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  规格 前端控制器
 * </p>
 *
 * @author xt
 * @since 2020-01-05
 */
@Api(value = "specification", description = "规格管理-消费端")
@RestController
@RequestMapping("/consumer/specification")
public class SpecificationController {
    
    @Reference
    private SpecificationService specificationService;

    @ApiOperation(value = "查询规格详细信息", notes = "规格详情")
    @GetMapping("/{id}")
    public SpecificationGroup get(@ApiParam(value = "规格ID", required = true) @PathVariable("id") Long id) {
        return specificationService.getBySpecId(id);
    }

    @ApiOperation(value = "获得规格列表", notes = "列表信息")
    @GetMapping("/list")
    public List<Specification> list() {
        return specificationService.list();
    }

    @ApiOperation(value = "添加规格", notes = "添加规格")
    @PostMapping
    public Result add(@RequestBody SpecificationGroup specificationGroup) {
        Result result = new Result();
        try {
            boolean flag = specificationService.saveGroup(specificationGroup);
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

    @ApiOperation(value = "更新规格", notes = "更新规格")
    @PutMapping
    public Result update(@RequestBody SpecificationGroup specificationGroup) {
        Result result = new Result();
        try {
            boolean flag = specificationService.updateGroup(specificationGroup);
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

    @ApiOperation(value = "删除规格", notes = "删除规格")
    @DeleteMapping("/{id}")
    public Result delete(@ApiParam(value = "规格ID", required = true) @PathVariable("id") Long id) {
        Result result = new Result();
        try {
            boolean flag = specificationService.removeGroupById(id);
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

    @ApiOperation(value = "批量删除规格", notes = "批量删除规格")
    @DeleteMapping("/deleteBatch/{ids}")
    public Result deleteBatch(@ApiParam(value = "规格IDs", required = true) @PathVariable("ids") Long[] ids) {
        Result result = new Result();
        try {
            boolean flag = specificationService.removeGroupByIds(Arrays.asList(ids));
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
     * @param specification
     * @return
     */
    @ApiOperation(value = "获得规格分页列表", notes = "列表信息")
    @PostMapping("/page")
    public IPage<Specification> page(@ApiParam(value = "当前页码", required = true) Integer currentPage,
                             @ApiParam(value = "每页大小", required = true) Integer pageNum,
                             @RequestBody Specification specification) {

        return specificationService.selectPage(currentPage, pageNum, specification);
    }
}

