package com.xt.pinyougou.entity;

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
 * @since 2020-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_goods_desc")
@ApiModel(value="GoodsDesc对象", description="商品描述")
public class GoodsDesc implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "SPU_ID")
    @TableId(value = "goods_id")
    private Long goodsId;

    @ApiModelProperty(value = "描述")
    private String introduction;

    @ApiModelProperty(value = "规格结果集，所有规格，包含isSelected")
    private String specificationItems;

    @ApiModelProperty(value = "自定义属性（参数结果）")
    private String customAttributeItems;

    @ApiModelProperty(value = "图片列表")
    private String itemImages;

    @ApiModelProperty(value = "包装列表")
    private String packageList;

    @ApiModelProperty(value = "售后服务")
    private String saleService;


}
