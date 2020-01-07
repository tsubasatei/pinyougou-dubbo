package com.xt.pinyougou.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xt.pinyougou.entity.Seller;
import com.xt.pinyougou.mapper.SellerMapper;
import com.xt.pinyougou.service.SellerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * <p>
 *  商家 服务实现类
 * </p>
 *
 * @author xt
 * @since 2020-01-07
 */
@Service(timeout = 5000)
public class SellerServiceImpl extends ServiceImpl<SellerMapper, Seller> implements SellerService {

}
