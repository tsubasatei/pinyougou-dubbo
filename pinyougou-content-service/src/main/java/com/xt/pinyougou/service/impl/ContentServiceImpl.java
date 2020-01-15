package com.xt.pinyougou.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xt.pinyougou.entity.Content;
import com.xt.pinyougou.mapper.ContentMapper;
import com.xt.pinyougou.service.ContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 *  广告 服务实现类
 * </p>
 *
 * @author xt
 * @since 2020-01-11
 */
@Service(timeout = 5000)
@CacheConfig(cacheNames = "content")
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements ContentService {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Transactional(readOnly = true)
    @Override
    public IPage<Content> selectPage(Integer currentPage, Integer pageNum, Content content) {
        QueryWrapper<Content> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(content.getTitle())) {
            queryWrapper.lambda().like(Content::getTitle, content.getTitle());
        }
        return baseMapper.selectPage(new Page<>(currentPage, pageNum), queryWrapper);
    }

    // 根据广告类型ID查询列表
    @Cacheable
    @Transactional(readOnly = true)
    @Override
    public List<Content> findListByCategoryId(Long categoryId) {
        System.out.println("查询广告分类：" + categoryId);
        QueryWrapper<Content> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Content::getCategoryId, categoryId)
                .eq(Content::getStatus,"1")//开启状态
                .orderByAsc(Content::getSortOrder); //排序
        return baseMapper.selectList(queryWrapper);
    }

    @Transactional
    @Override
    public boolean insert(Content content) {
        int insert = baseMapper.insert(content);
        // 清除缓存
        Object old = redisTemplate.opsForValue().get("content::" + content.getCategoryId());
        System.out.println("原缓存：" + old);
        redisTemplate.delete("content::" + content.getCategoryId());
        System.out.println("缓存清除成功");
        return insert == 1;
    }

    @Transactional
    @Override
    public boolean update(Content content) {
        // 1. 查询修改前的分类Id
        Long categoryId = baseMapper.selectById(content.getId()).getCategoryId();
        Object old = redisTemplate.opsForValue().get("content::" + categoryId);
        System.out.println("原缓存：" + old);
        redisTemplate.delete("content::" + categoryId);
        System.out.println("原缓存清除成功");
        int update = baseMapper.updateById(content);
        // 2. 如果分类ID发生了修改,清除修改后的分类ID的缓存
        if (!content.getCategoryId().equals(categoryId)) {
            redisTemplate.delete("content::" + content.getCategoryId());
        }
        return 1 == update;
    }

    @Transactional
    @Override
    public boolean deleteBatch(List<Long> ids) {
        for (Long id : ids) {
            // 清除缓存
            Long categoryId = baseMapper.selectById(id).getCategoryId();
            redisTemplate.delete("content::" + categoryId);
            int i = baseMapper.deleteById(id);
            if (i != 1) {
                return false;
            }
        }
        return true;
    }
}
