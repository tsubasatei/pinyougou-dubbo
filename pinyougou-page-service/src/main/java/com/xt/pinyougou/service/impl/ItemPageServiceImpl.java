package com.xt.pinyougou.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xt.pinyougou.entity.Goods;
import com.xt.pinyougou.entity.GoodsDesc;
import com.xt.pinyougou.entity.Item;
import com.xt.pinyougou.mapper.GoodsDescMapper;
import com.xt.pinyougou.mapper.GoodsMapper;
import com.xt.pinyougou.mapper.ItemCatMapper;
import com.xt.pinyougou.mapper.ItemMapper;
import com.xt.pinyougou.page.service.ItemPageService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品详情页
 */
@Service(timeout = 5000)
public class ItemPageServiceImpl implements ItemPageService {

    @Value("${pagedir}")
    private String pagedir;

    @Autowired
    private FreeMarkerConfig freeMarkerConfig;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsDescMapper goodsDescMapper;

    @Autowired
    private ItemCatMapper itemCatMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Transactional(readOnly = true)
    @Override
    public boolean genItemHtml(Long goodsId) {

        try {
            Configuration configuration = freeMarkerConfig.getConfiguration();
            Template template = configuration.getTemplate("item.ftl");
            Map<String, Object> dataModel = new HashMap<>();

            // 1 加载商品表数据
            Goods goods = goodsMapper.selectById(goodsId);
            dataModel.put("goods", goods);

            // 2 加载商品扩展表数据
            GoodsDesc goodsDesc = goodsDescMapper.selectById(goodsId);
            dataModel.put("goodsDesc", goodsDesc);

            // 3 商品分类
            String itemCat1 = itemCatMapper.selectById(goods.getCategory1Id()).getName();
            String itemCat2 = itemCatMapper.selectById(goods.getCategory2Id()).getName();
            String itemCat3 = itemCatMapper.selectById(goods.getCategory3Id()).getName();
            dataModel.put("itemCat1", itemCat1);
            dataModel.put("itemCat2", itemCat2);
            dataModel.put("itemCat3", itemCat3);

            // 4 SKU 列表
            QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda()
                    .eq(Item::getGoodsId, goodsId)
                    .eq(Item::getStatus, "1") // 状态为有效
                    .orderByDesc(Item::getIsDefault);// //按照状态降序，保证第一个为默认
            List<Item> itemList = itemMapper.selectList(queryWrapper);
            dataModel.put("itemList", itemList);

            Writer writer = new FileWriter(pagedir + goodsId + ".html");
            template.process(dataModel, writer);
            writer.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
