package com.xt.pinyougou.page.service;

/**
 * 商品详细页接口
 */
public interface ItemPageService {
    /**
     * 生成商品详细页
     * @param goodsId
     */
    boolean genItemHtml(Long goodsId);

    /**
     * 删除商品详细页
     * @param goodsIds
     * @return
     */
    boolean deleteItemHtml(Long[] goodsIds);

}
