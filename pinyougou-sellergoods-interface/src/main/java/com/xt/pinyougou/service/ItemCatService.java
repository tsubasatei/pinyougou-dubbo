package com.xt.pinyougou.service;

import com.xt.pinyougou.entity.ItemCat;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品类目 服务类
 * </p>
 *
 * @author xt
 * @since 2020-01-07
 */
public interface ItemCatService extends IService<ItemCat> {
    /**
     * 根据上级ID返回列表
     */
    List<ItemCat> findByParentId(Long parentId);

    boolean deleteBatch(List<Long> ids);
}
