package com.xt.pinyougou.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xt.pinyougou.entity.*;
import com.xt.pinyougou.mapper.*;
import com.xt.pinyougou.pojo.GoodsGroup;
import com.xt.pinyougou.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xt
 * @since 2020-01-07
 */
@Service(timeout = 5000)
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    private GoodsDescMapper goodsDescMapper;
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private ItemCatMapper itemCatMapper;
    @Autowired
    private SellerMapper sellerMapper;
    @Autowired
    private ItemMapper itemMapper;

    @Transactional(readOnly = true)
    @Override
    public IPage<Goods> selectPage(Integer currentPage, Integer pageNum, Goods goods) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper();
        if (!StringUtils.isEmpty(goods.getGoodsName())) {
            queryWrapper.lambda().like(Goods::getGoodsName, goods.getGoodsName());
        }
        if (!StringUtils.isEmpty(goods.getAuditStatus())) {
            queryWrapper.lambda().eq(Goods::getAuditStatus, goods.getAuditStatus());
        }
        if (!StringUtils.isEmpty(goods.getSellerId())) {
            queryWrapper.lambda().eq(Goods::getSellerId, goods.getSellerId());
        }
        return baseMapper.selectPage(new Page<>(currentPage, pageNum), queryWrapper);
    }

    @Transactional(readOnly = true)
    @Override
    public GoodsGroup findOne(Long id) {
        GoodsGroup goodsGroup = new GoodsGroup();
        Goods goods = baseMapper.selectById(id);
        GoodsDesc goodsDesc = goodsDescMapper.selectById(goods.getId());
        List<Item> items = itemMapper.selectList(new QueryWrapper<Item>().lambda().eq(Item::getGoodsId, goods.getId()));
        goodsGroup.setGoods(goods).setGoodsDesc(goodsDesc).setItemList(items);
        return goodsGroup;
    }

    @Transactional
    @Override
    public boolean add(GoodsGroup goodsGroup) {

        // 保存商品基本信息
        int insert = baseMapper.insert(goodsGroup.getGoods());
        if (1 != insert) {
            return false;
        }

        //插入商品扩展数据
        goodsGroup.getGoodsDesc().setGoodsId(goodsGroup.getGoods().getId()); // 设置商品ID
        int insert1 = goodsDescMapper.insert(goodsGroup.getGoodsDesc());
        if (1 != insert1) {
            return false;
        }

        return saveItemList(goodsGroup);
    }

    // 插入商品SKU列表数据
    private boolean saveItemList(GoodsGroup goodsGroup) {
        // 商品SPU
        Goods goods = goodsGroup.getGoods();
        // 商品扩展
        GoodsDesc goodsDesc = goodsGroup.getGoodsDesc();

        if ("1".equals(goods.getIsEnableSpec())) {  // 启用规格
            //商品SKU列表
            List<Item> itemList = goodsGroup.getItemList();
            for (Item item : itemList) {
                // 标题
                String title = goods.getGoodsName();
                Map<String, Object> specMap = JSON.parseObject(item.getSpec());
                for (String key : specMap.keySet()) {
                    title += "" + specMap.get(key);
                }
                item.setTitle(title);
                setItemValues(goods, goodsDesc, item);
                int insert = itemMapper.insert(item);
                if (1 != insert) {
                    return false;
                }
            }
        } else { // 不启用规格
            Item item = new Item();
            // 商品KPU+规格描述串作为SKU名称
            item.setTitle(goods.getGoodsName());
            // 价格
            item.setPrice(goods.getPrice());
            // 状态
            item.setStatus("1");
            // 是否默认
            item.setIsDefault("1");
            // 库存数量
            item.setNum(99999);
            // 规格
            item.setSpec("{}");
            setItemValues(goods, goodsDesc, item);
            int insert = itemMapper.insert(item);
            if (1 != insert) {
                return false;
            }
        }
        return true;
    }

    private void setItemValues(Goods goods, GoodsDesc goodsDesc, Item item) {
        // 商品SPU编号
        item.setGoodsId(goods.getId());
        // 商家编号
        item.setSellerId(goods.getSellerId());
        // 商家名称
        Seller seller = sellerMapper.selectById(goods.getSellerId());
        item.setSeller(seller.getNickName());
        // 创建日期
        item.setCreateTime(LocalDateTime.now());
        // 修改日期
        item.setUpdateTime(LocalDateTime.now());
        // 品牌名称
        Brand brand = brandMapper.selectById(goods.getBrandId());
        item.setBrand(brand.getName());
        // 商品分类编号（3级）
        item.setCategoryId(goods.getCategory3Id());
        // 分类名称
        ItemCat itemCat = itemCatMapper.selectById(goods.getCategory3Id());
        item.setCategory(itemCat.getName());
        // 图片地址（取SPU的第一个图片）
        List<Map> imageList = JSON.parseArray(goodsDesc.getItemImages(), Map.class);
        if (imageList.size() > 0) {
            item.setImage((String) imageList.get(0).get("url"));
        }
    }

    @Transactional
    @Override
    public boolean updateGroup(GoodsGroup goodsGroup) {
        int update = baseMapper.updateById(goodsGroup.getGoods());
        if (1 != update) {
            return false;
        }
        int update1 = goodsDescMapper.updateById(goodsGroup.getGoodsDesc());
        if (1 != update1) {
            return false;
        }
        // 删除原有的SKU列表
        int delete = itemMapper.delete(new QueryWrapper<Item>().lambda().eq(Item::getGoodsId, goodsGroup.getGoods().getId()));
        if (delete < 1) {
            return false;
        }
        //添加新的sku列表数据
        return saveItemList(goodsGroup);
    }

    @Transactional
    @Override
    public boolean updateMarketable(Long id, String marketable) {
        Goods goods = baseMapper.selectById(id);
        if (null == goods) {
            return false;
        }
        goods.setIsMarketable(marketable);
        int i = baseMapper.updateById(goods);
        return i == 1;
    }

    @Transactional
    @Override
    public boolean updateStatus(List<Long> ids, String status) {
        List<Goods> goodsList = baseMapper.selectBatchIds(ids);
        for (Goods goods : goodsList) {
            goods.setAuditStatus(status);
            int i = baseMapper.updateById(goods);
            if (1 != i) {
                return false;
            }
        }
        return true;
    }

}
