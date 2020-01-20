package com.xt.pinyougou.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xt.pinyougou.entity.ItemCat;
import com.xt.pinyougou.mapper.ItemCatMapper;
import com.xt.pinyougou.service.ItemCatService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
@Service(timeout = 5000)
public class ItemCatServiceImpl extends ServiceImpl<ItemCatMapper, ItemCat> implements ItemCatService {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 根据上级ID查询列表
     */
    @Transactional(readOnly = true)
    @Override
    public List<ItemCat> findByParentId(Long parentId) {
        QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ItemCat::getParentId, parentId);

        // 每次执行查询的时候，一次性读取缓存进行存储 (因为每次增删改都要执行此方法)
        List<ItemCat> itemCats = baseMapper.selectList(null);
        for (ItemCat itemCat : itemCats) {
//            redisTemplate.boundHashOps("itemCat").put(itemCat.getName(), itemCat.getTypeId());
            redisTemplate.opsForHash().put("itemCat", itemCat.getName(), itemCat.getTypeId());
        }
        System.out.println("更新缓存：商品分类列表");
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
            int result = baseMapper.deleteById(id);
            return result == 1;
        }
        return true;
    }
}
