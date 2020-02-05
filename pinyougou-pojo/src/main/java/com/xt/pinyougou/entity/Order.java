package com.xt.pinyougou.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
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
@TableName("tb_order")
@ApiModel(value="Order对象", description="订单")
public class Order implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "订单id")
    @TableId(value = "order_id", type = IdType.AUTO)
    private Long orderId;

    @ApiModelProperty(value = "实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分")
    private BigDecimal payment;

    @ApiModelProperty(value = "支付类型，1、在线支付，2、货到付款")
    private String paymentType;

    @ApiModelProperty(value = "邮费。精确到2位小数;单位:元。如:200.07，表示:200元7分")
    private String postFee;

    @ApiModelProperty(value = "状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭,7、待评价")
    private String status;

    @ApiModelProperty(value = "订单创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "订单更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "付款时间")
    private LocalDateTime paymentTime;

    @ApiModelProperty(value = "发货时间")
    private LocalDateTime consignTime;

    @ApiModelProperty(value = "交易完成时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "交易关闭时间")
    private LocalDateTime closeTime;

    @ApiModelProperty(value = "物流名称")
    private String shippingName;

    @ApiModelProperty(value = "物流单号")
    private String shippingCode;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "买家留言")
    private String buyerMessage;

    @ApiModelProperty(value = "买家昵称")
    private String buyerNick;

    @ApiModelProperty(value = "买家是否已经评价")
    private String buyerRate;

    @ApiModelProperty(value = "收货人地区名称(省，市，县)街道")
    private String receiverAreaName;

    @ApiModelProperty(value = "收货人手机")
    private String receiverMobile;

    @ApiModelProperty(value = "收货人邮编")
    private String receiverZipCode;

    @ApiModelProperty(value = "收货人")
    private String receiver;

    @ApiModelProperty(value = "过期时间，定期清理")
    private LocalDateTime expire;

    @ApiModelProperty(value = "发票类型(普通发票，电子发票，增值税发票)")
    private String invoiceType;

    @ApiModelProperty(value = "订单来源：1:app端，2：pc端，3：M端，4：微信端，5：手机qq端")
    private String sourceType;

    @ApiModelProperty(value = "商家ID")
    private String sellerId;


}
