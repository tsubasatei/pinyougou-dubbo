package com.xt.pinyougou.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xt.pinyougou.entity.Brand;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  品牌服务类
 * </p>
 *
 * @author xt
 * @since 2019-12-24
 */
public interface BrandService extends IService<Brand> {

    IPage<Brand> selectPage(Integer currentPage, Integer pageNum, Brand brand);

    /**
     * 读取品牌列表
     * @return
     */
    List<Map<String, Object>> selectOptionList();
}
