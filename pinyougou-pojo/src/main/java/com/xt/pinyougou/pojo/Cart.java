package com.xt.pinyougou.pojo;

import com.xt.pinyougou.entity.OrderItem;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 购物车类
 *
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Cart对象", description="购物车实体类")
public class Cart implements Serializable {

    private String sellerId; //商家ID
    private String sellerName; //商家名称
    private List<OrderItem> orderItemList; //购物车明细

}
