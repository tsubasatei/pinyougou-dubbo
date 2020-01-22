package com.xt.pinyougou.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.Dynamic;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author xt
 * @since 2020-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_item")
@ApiModel(value="Item对象", description="商品表")
public class Item implements Serializable {

    private static final long serialVersionUID=1L;

    @Field
    @ApiModelProperty(value = "商品id，同时也是商品编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Field("item_title")
    @ApiModelProperty(value = "商品标题")
    private String title;

    @ApiModelProperty(value = "商品卖点")
    private String sellPoint;

    @Field("item_price")
    @ApiModelProperty(value = "商品价格，单位为：元")
    private Double price;

    private Integer stockCount;

    @ApiModelProperty(value = "库存数量")
    private Integer num;

    @ApiModelProperty(value = "商品条形码")
    private String barcode;

    @Field("item_image")
    @ApiModelProperty(value = "商品图片")
    private String image;

    @ApiModelProperty(value = "所属类目，叶子类目")
    @TableField("categoryId")
    private Long categoryId;

    @ApiModelProperty(value = "商品状态，1-正常，2-下架，3-删除")
    private String status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @Field("item_updatetime")
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    private String itemSn;

    private BigDecimal costPirce;

    private BigDecimal marketPrice;

    private String isDefault;

    @Field("item_goodsid")
    private Long goodsId;

    private String sellerId;

    private String cartThumbnail;

    @Field("item_category")
    private String category;

    @Field("item_brand")
    private String brand;

    private String spec;

    @Field("item_seller")
    private String seller;

    @Dynamic
    @Field("item_spec_*")
    @TableField(exist = false)
    private Map<String, String> specMap;

}
