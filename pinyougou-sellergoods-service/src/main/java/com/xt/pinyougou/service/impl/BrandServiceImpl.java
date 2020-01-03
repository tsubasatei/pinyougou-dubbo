package com.xt.pinyougou.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xt.pinyougou.entity.Brand;
import com.xt.pinyougou.mapper.BrandMapper;
import com.xt.pinyougou.service.BrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * <p>
 *  品牌服务实现类
 * </p>
 *
 * @author xt
 * @since 2019-12-24
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

}
