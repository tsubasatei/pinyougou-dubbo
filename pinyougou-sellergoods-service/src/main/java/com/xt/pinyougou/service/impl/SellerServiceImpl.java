package com.xt.pinyougou.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xt.pinyougou.entity.Seller;
import com.xt.pinyougou.mapper.SellerMapper;
import com.xt.pinyougou.service.SellerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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

    @Transactional
    @Override
    public boolean updateStatus(String sellerId, String status) {
        Seller seller = baseMapper.selectById(sellerId);
        seller.setStatus(status);
        int result = baseMapper.updateById(seller);
        return result == 1;
    }

    @Transactional(readOnly = true)
    @Override
    public IPage<Seller> selectPage(Integer currentPage, Integer pageNum, Seller seller) {
        QueryWrapper<Seller> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(seller.getStatus())) {
            queryWrapper.lambda().eq(Seller::getStatus, seller.getStatus());
        }
        if (!StringUtils.isEmpty(seller.getName())) {
            queryWrapper.lambda().eq(Seller::getName, seller.getName());
        }
        if (!StringUtils.isEmpty(seller.getName())) {
            queryWrapper.lambda().eq(Seller::getNickName, seller.getNickName());
        }
        return baseMapper.selectPage(new Page<>(currentPage, pageNum), queryWrapper);
    }
}
