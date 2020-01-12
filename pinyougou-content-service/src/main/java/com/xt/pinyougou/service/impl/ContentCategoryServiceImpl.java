package com.xt.pinyougou.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xt.pinyougou.entity.ContentCategory;
import com.xt.pinyougou.mapper.ContentCategoryMapper;
import com.xt.pinyougou.service.ContentCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 广告 内容分类 服务实现类
 * </p>
 *
 * @author xt
 * @since 2020-01-11
 */
@Service(timeout = 5000)
public class ContentCategoryServiceImpl extends ServiceImpl<ContentCategoryMapper, ContentCategory> implements ContentCategoryService {

    @Transactional(readOnly = true)
    @Override
    public IPage<ContentCategory> selectPage(Integer currentPage, Integer pageNum, ContentCategory contentCategory) {
        QueryWrapper<ContentCategory> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(contentCategory.getName())) {
            queryWrapper.lambda().like(ContentCategory::getName, contentCategory.getName());
        }
        return baseMapper.selectPage(new Page<>(currentPage, pageNum), queryWrapper);
    }
}
