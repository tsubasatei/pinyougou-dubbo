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
 * 商品类目
 * </p>
 *
 * @author xt
 * @since 2020-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_item_cat")
@ApiModel(value="ItemCat对象", description="商品类目")
public class ItemCat implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "类目ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "父类目ID=0时，代表的是一级的类目")
    private Long parentId;

    @ApiModelProperty(value = "类目名称")
    private String name;

    @ApiModelProperty(value = "类型id")
    private Long typeId;


}
