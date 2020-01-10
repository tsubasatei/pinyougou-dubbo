package com.xt.pinyougou.pojo;

import com.xt.pinyougou.entity.Goods;
import com.xt.pinyougou.entity.GoodsDesc;
import com.xt.pinyougou.entity.Item;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="GoodsGroup对象", description="商品组合实体类")
public class GoodsGroup implements Serializable {
    private Goods goods; // 商品SPU
    private GoodsDesc goodsDesc; // 商品扩展
    private List<Item> itemList; //商品SKU列表

}
