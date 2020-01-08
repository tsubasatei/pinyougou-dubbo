package com.xt.pinyougou.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xt.pinyougou.entity.ItemCat;
import com.xt.pinyougou.mapper.ItemCatMapper;
import com.xt.pinyougou.service.ItemCatService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 商品类目 服务实现类
 * </p>
 *
 * @author xt
 * @since 2020-01-07
 */
@Service
public class ItemCatServiceImpl extends ServiceImpl<ItemCatMapper, ItemCat> implements ItemCatService {

    @Transactional(readOnly = true)
    @Override
    public List<ItemCat> findByParentId(Long parentId) {
        QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ItemCat::getParentId, parentId);
        return baseMapper.selectList(queryWrapper);
    }

    @Transactional
    @Override
    public boolean deleteBatch(List<Long> ids) {
        for (Long id : ids) {
            List<ItemCat> catChildren = this.findByParentId(id);
            if (catChildren != null && catChildren.size() > 0) {
                return false;
            }
            baseMapper.deleteById(id);
        }
        return true;
    }
}
