package com.xt.pinyougou.service;

import com.xt.pinyougou.entity.Item;

import java.util.List;
import java.util.Map;

/**
 * 商品搜索接口
 */
public interface ItemSearchService {

    /**
     * 搜索
     * @param searchMap
     * @return
     */
    Map<String, Object> search(Map searchMap);

    /**
     * 导入数据
     * @param list
     */
    void importList(List<Item> list);

    /**
     * 删除数据
     * @param goodsIds
     */
    void deleteByGoodsIds(List<Long> goodsIds);
}
