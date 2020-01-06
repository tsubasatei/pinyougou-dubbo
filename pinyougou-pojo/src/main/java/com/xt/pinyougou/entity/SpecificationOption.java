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
 * @since 2020-01-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_specification_option")
@ApiModel(value="SpecificationOption对象", description="规格选项")
public class SpecificationOption implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "规格项ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "规格项名称")
    private String optionName;

    @ApiModelProperty(value = "规格ID")
    private Long specId;

    @ApiModelProperty(value = "排序值")
    private Integer orders;


}
