package com.xt.pinyougou.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xt.pinyougou.entity.Brand;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
