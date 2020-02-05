package com.xt.pinyougou.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author xt
 * @since 2020-02-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_order_item")
@ApiModel(value="OrderItem对象", description="订单明细")
public class OrderItem implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "商品id")
    private Long itemId;

    @ApiModelProperty(value = "SPU_ID")
    private Long goodsId;

    @ApiModelProperty(value = "订单id")
    private Long orderId;

    @ApiModelProperty(value = "商品标题")
    private String title;

    @ApiModelProperty(value = "商品单价")
    private BigDecimal price;

    @ApiModelProperty(value = "商品购买数量")
    private Integer num;

    @ApiModelProperty(value = "商品总金额")
    private BigDecimal totalFee;

    @ApiModelProperty(value = "商品图片地址")
    private String picPath;

    private String sellerId;


}
