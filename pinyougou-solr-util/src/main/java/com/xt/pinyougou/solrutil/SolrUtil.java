package com.xt.pinyougou.solrutil;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xt.pinyougou.entity.Item;
import com.xt.pinyougou.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * solr工具类，导入数据
 */
@Component
public class SolrUtil {

    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private SolrTemplate solrTemplate;

    /**
     * 导入商品数据
     */
    public void importItemData() {
        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Item::getStatus, "1"); // 已审核
        List<Item> items = itemMapper.selectList(queryWrapper);
        System.out.println("====商品列表====");
        items.forEach(item -> {
            Map<String, String> map = JSON.parseObject(item.getSpec(), Map.class); //将spec字段中的json字符串转换为map
            // solr7之后版本的动态域,不能使用中文作为拼接索引, 将其使用URLEncoder.encode()方法格式为UTF-8
            Map<String, String> specMap = new HashMap<>();
            map.entrySet().stream().forEach(x -> {
                try {
                    specMap.put(URLEncoder.encode(x.getKey(), "UTF-8"), x.getValue());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            });
            item.setSpecMap(specMap); // 给带注解的字段赋值
            System.out.println(item.getTitle() + " --> " + item.getSpecMap());
        });
        System.out.println("总条数：" + items.size());
        solrTemplate.saveBeans("pyg_db", items);
        solrTemplate.commit("pyg_db");
        System.out.println("======结束======");
    }

    public void deleteAll() {
        Query query = new SimpleQuery("*:*");
        solrTemplate.delete("pyg_db", query);
        solrTemplate.commit("pyg_db");
    }
}
