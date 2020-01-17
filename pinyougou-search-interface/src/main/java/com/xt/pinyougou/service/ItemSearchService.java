package com.xt.pinyougou.service;

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
}
