package com.xt.pinyougou.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xt.pinyougou.entity.Content;
import com.xt.pinyougou.mapper.ContentMapper;
import com.xt.pinyougou.service.ContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  广告 服务实现类
 * </p>
 *
 * @author xt
 * @since 2020-01-11
 */
@Service(timeout = 5000)
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements ContentService {

    @Transactional(readOnly = true)
    @Override
    public IPage<Content> selectPage(Integer currentPage, Integer pageNum, Content content) {
        QueryWrapper<Content> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(content.getTitle())) {
            queryWrapper.lambda().like(Content::getTitle, content.getTitle());
        }
        return baseMapper.selectPage(new Page<>(currentPage, pageNum), queryWrapper);
    }
}
