package com.xt.pinyougou.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xt.pinyougou.entity.Goods;
import com.xt.pinyougou.pojo.GoodsGroup;

import java.util.List;

/**
 * <p>
 *  商品 服务类
 * </p>
 *
 * @author xt
 * @since 2020-01-07
 */
public interface GoodsService extends IService<Goods> {
    boolean add(GoodsGroup goodsGroup);

    IPage<Goods> selectPage(Integer currentPage, Integer pageNum, Goods goods);

    GoodsGroup findOne(Long id);

    boolean updateGroup(GoodsGroup goodsGroup);

    boolean updateStatus(List<Long> ids, String status);

    boolean updateMarketable(Long id, String marketable);
}
