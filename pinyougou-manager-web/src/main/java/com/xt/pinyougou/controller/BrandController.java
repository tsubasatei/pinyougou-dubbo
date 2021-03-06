package com.xt.pinyougou.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xt.bean.Result;
import com.xt.pinyougou.entity.Brand;
import com.xt.pinyougou.service.BrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  品牌管理前端控制器
 * </p>
 *
 */
@Api(value = "brand", description = "品牌管理-消费端")
@RestController
@RequestMapping("/consumer/brand")
public class BrandController {


    @Reference
    private BrandService brandService;

    @ApiOperation(value = "查询品牌详细信息", notes = "品牌详情")
    @GetMapping("/{id}")
    public Brand get(@ApiParam(value = "品牌ID", required = true) @PathVariable("id") Long id) {
        return brandService.getById(id);
    }

    @ApiOperation(value = "获得品牌列表", notes = "列表信息")
    @GetMapping("/list")
    public List<Brand> list() {
        return brandService.list();
    }

    @ApiOperation(value = "添加品牌", notes = "添加品牌")
    @PostMapping
    public Result add(@RequestBody Brand brand) {
        Result result = new Result();
        try {
            boolean flag = brandService.save(brand);
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

    @ApiOperation(value = "更新品牌", notes = "更新品牌")
    @PutMapping
    public Result update(@RequestBody Brand brand) {
        Result result = new Result();
        try {
            boolean flag = brandService.updateById(brand);
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

    @ApiOperation(value = "删除品牌", notes = "删除品牌")
    @DeleteMapping("/{id}")
    public Result delete(@ApiParam(value = "品牌ID", required = true) @PathVariable("id") Long id) {
        Result result = new Result();
        try {
            boolean flag = brandService.removeById(id);
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

    @ApiOperation(value = "批量删除品牌", notes = "批量删除品牌")
    @DeleteMapping("/deleteBatch/{ids}")
    public Result deleteBatch(@ApiParam(value = "品牌IDs", required = true) @PathVariable("ids") Long[] ids) {
        Result result = new Result();
        try {
            boolean flag = brandService.removeByIds(Arrays.asList(ids));
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
     * @param brand
     * @return
     */
    @ApiOperation(value = "获得品牌分页列表", notes = "列表信息")
    @PostMapping("/page")
    public IPage<Brand> page(@ApiParam(value = "当前页码", required = true) Integer currentPage,
                             @ApiParam(value = "每页大小", required = true) Integer pageNum,
                             @RequestBody Brand brand) {

        return brandService.selectPage(currentPage, pageNum, brand);
    }

    @ApiOperation(value = "读取品牌列表", notes = "列表信息")
    @GetMapping("/findBrandList")
    public List<Map<String, Object>> findBrandList() {
        return brandService.selectOptionList();
    }
}

