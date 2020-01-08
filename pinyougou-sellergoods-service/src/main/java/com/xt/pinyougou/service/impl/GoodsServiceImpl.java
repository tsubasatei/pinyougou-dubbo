package com.xt.pinyougou.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xt.pinyougou.entity.Goods;
import com.xt.pinyougou.mapper.GoodsMapper;
import com.xt.pinyougou.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xt
 * @since 2020-01-07
 */
@Service(timeout = 5000)
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

}
