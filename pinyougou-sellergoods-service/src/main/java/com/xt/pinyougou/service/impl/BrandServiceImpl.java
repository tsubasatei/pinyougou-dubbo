package com.xt.pinyougou.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xt.pinyougou.entity.Brand;
import com.xt.pinyougou.mapper.BrandMapper;
import com.xt.pinyougou.service.BrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  品牌服务实现类
 * </p>
 */
@Service(timeout = 5000)
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

    @Override
    public IPage<Brand> selectPage(Integer currentPage, Integer pageNum, Brand brand) {

        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(brand.getName())) {
//            queryWrapper.like("name", brand.getName());
            queryWrapper.lambda().like(Brand::getName, brand.getName());
        }
        if (!StringUtils.isEmpty(brand.getFirstChar())) {
//            queryWrapper.like("first_char", brand.getFirstChar());
            queryWrapper.lambda().like(Brand::getFirstChar, brand.getFirstChar());
        }
        IPage<Brand>  result = baseMapper.selectPage(new Page<>(currentPage, pageNum), queryWrapper);
        return result;

    }
}
