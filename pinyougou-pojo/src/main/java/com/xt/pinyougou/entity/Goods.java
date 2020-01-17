package com.xt.pinyougou.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
@TableName("tb_goods")
@ApiModel(value="Goods对象", description="商品")
public class Goods implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "商家ID")
    private String sellerId;

    @ApiModelProperty(value = "SPU名")
    private String goodsName;

    @ApiModelProperty(value = "默认SKU")
    private Long defaultItemId;

    @ApiModelProperty(value = "状态")
    private String auditStatus;

    @ApiModelProperty(value = "是否上架")
    private String isMarketable;

    @ApiModelProperty(value = "品牌")
    private Long brandId;

    @ApiModelProperty(value = "副标题")
    private String caption;

    @ApiModelProperty(value = "一级类目")
    private Long category1Id;

    @ApiModelProperty(value = "二级类目")
    private Long category2Id;

    @ApiModelProperty(value = "三级类目")
    private Long category3Id;

    @ApiModelProperty(value = "小图")
    private String smallPic;

    @ApiModelProperty(value = "商城价")
    private Double price;

    @ApiModelProperty(value = "分类模板ID")
    private Long typeTemplateId;

    @ApiModelProperty(value = "是否启用规格")
    private String isEnableSpec;

    @TableLogic
    @ApiModelProperty(value = "是否删除")
    private String isDelete;


}
