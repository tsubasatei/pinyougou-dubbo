package com.xt.pinyougou.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xt.pinyougou.entity.Content;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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

    List<Content> findListByCategoryId(Long categoryId);

    boolean insert(Content content);

    boolean update(Content content);

    boolean deleteBatch(List<Long> asList);
}
