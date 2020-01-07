package com.xt.pinyougou.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xt.pinyougou.entity.Seller;

/**
 * <p>
 *  商家 服务类
 * </p>
 *
 * @author xt
 * @since 2020-01-07
 */
public interface SellerService extends IService<Seller> {

    IPage<Seller> selectPage(Integer currentPage, Integer pageNum, Seller seller);

    boolean updateStatus(String sellerId, String status);
}
