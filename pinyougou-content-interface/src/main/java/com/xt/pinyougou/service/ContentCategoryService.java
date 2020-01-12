package com.xt.pinyougou.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xt.pinyougou.entity.ContentCategory;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 广告内容分类 服务类
 * </p>
 *
 * @author xt
 * @since 2020-01-11
 */
public interface ContentCategoryService extends IService<ContentCategory> {

    IPage<ContentCategory> selectPage(Integer currentPage, Integer pageNum, ContentCategory contentCategory);
}
