package com.xt.pinyougou.service.impl;

import com.xt.pinyougou.entity.Item;
import com.xt.pinyougou.mapper.ItemMapper;
import com.xt.pinyougou.service.ItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author xt
 * @since 2020-01-07
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {

}
