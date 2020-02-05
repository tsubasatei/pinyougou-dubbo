package com.xt.pinyougou.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xt.pinyougou.entity.OrderItem;
import com.xt.pinyougou.mapper.OrderItemMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xt
 * @since 2020-02-02
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements IService<OrderItem> {

}
