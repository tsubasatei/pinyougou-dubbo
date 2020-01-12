package com.xt.pinyougou.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xt.pinyougou.entity.Content;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  广告 服务类
 * </p>
 *
 * @author xt
 * @since 2020-01-11
 */
public interface ContentService extends IService<Content> {

    IPage<Content> selectPage(Integer currentPage, Integer pageNum, Content content);
}
