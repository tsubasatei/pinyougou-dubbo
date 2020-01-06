package com.xt.pinyougou.pojo;

import com.xt.pinyougou.entity.Specification;
import com.xt.pinyougou.entity.SpecificationOption;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 规格及规格选项组合实体类
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SpecificationGroup对象", description="规格及规格选项组合实体类")
public class SpecificationGroup implements Serializable {
    private Specification specification;
    private List<SpecificationOption> specificationOptions;
}
