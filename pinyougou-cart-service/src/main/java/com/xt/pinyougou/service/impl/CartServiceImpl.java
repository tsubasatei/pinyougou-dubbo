package com.xt.pinyougou.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xt.pinyougou.entity.Item;
import com.xt.pinyougou.entity.OrderItem;
import com.xt.pinyougou.mapper.ItemMapper;
import com.xt.pinyougou.pojo.Cart;
import com.xt.pinyougou.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车服务实现类
 */
@Service(timeout = 5000)
public class CartServiceImpl implements CartService {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public List<Cart> addGoodsToCartList(List<Cart> cartList, Long itemId, Integer num) {
        // 1.根据商品SKU ID查询SKU商品信息
        Item item = itemMapper.selectById(itemId);
        if (item == null) {
            throw new RuntimeException("商品不存在");
        }
        if (!item.getStatus().equals("1")) {
            throw new RuntimeException("商品状态无效");
        }
        // 2.获取商家ID
        String sellerId = item.getSellerId();

        // 3.根据商家ID判断购物车列表中是否存在该商家的购物车
        Cart cart = searchCartBySellerId(cartList, sellerId);
        // 4.如果购物车列表中不存在该商家的购物车
        if (cart == null) {
            // 4.1 新建购物车对象
            cart = new Cart();
            cart.setSellerId(sellerId)
                .setSellerName(item.getSeller());
            OrderItem orderItem = createOrderItem(item, num);
            List<OrderItem> orderItemList = new ArrayList<>();
            orderItemList.add(orderItem);
            cart.setOrderItemList(orderItemList);

            // 4.2 将新建的购物车对象添加到购物车列表
            cartList.add(cart);
        } else {
            // 5.如果购物车列表中存在该商家的购物车
            // 查询购物车明细列表中是否存在该商品
            OrderItem orderItem = searchOrderItemByItemId(cart.getOrderItemList(), itemId);
            if (orderItem == null) {
                // 5.1. 如果没有，新增购物车明细
                orderItem = createOrderItem(item, num);
                cart.getOrderItemList().add(orderItem);
            } else {
                // 5.2. 如果有，在原购物车明细上添加数量，更改金额
                orderItem.setNum(orderItem.getNum() + num)
                        .setTotalFee(orderItem.getPrice().multiply(new BigDecimal(orderItem.getNum())));
                // 如果数量操作后小于等于0，则移除
                if (orderItem.getNum() <= 0) {
                    cart.getOrderItemList().remove(orderItem); //移除购物车明细
                }

                // 如果移除后cart的明细数量为0，则将cart移除
                if (cart.getOrderItemList().size() == 0) {
                    cartList.remove(cart);
                }
            }
        }

        return cartList;
    }

    /**
     * 根据商家 ID 查询购物车对象
     */
    private Cart searchCartBySellerId(List<Cart> cartList, String sellerId) {
        for (Cart cart : cartList) {
            if (cart.getSellerId().equals(sellerId)) {
                return cart;
            }
        }
        return null;
    }

    /**
     * 创建订单明细
     * @param item
     * @param num
     * @return
     */
    private OrderItem createOrderItem(Item item, Integer num) {
        if (num <= 0) {
            throw new RuntimeException("数量非法");
        }
        OrderItem orderItem = new OrderItem();
        orderItem.setGoodsId(item.getGoodsId())
                .setItemId(item.getId())
                .setNum(num)
                .setPicPath(item.getImage())
                .setPrice(new BigDecimal(item.getPrice()))
                .setSellerId(item.getSellerId())
                .setTitle(item.getTitle())
                .setTotalFee(new BigDecimal(item.getPrice() * num));
        return orderItem;
    }

    /**
     * 查询购物车明细列表中是否存在该商品
     * @param orderItemList
     * @param itemId
     * @return
     */
    private OrderItem searchOrderItemByItemId(List<OrderItem> orderItemList, Long itemId) {
        for (OrderItem orderItem : orderItemList) {
            if (orderItem.getItemId().longValue() == itemId.longValue()) {
                return orderItem;
            }
        }
        return null;
    }
}
