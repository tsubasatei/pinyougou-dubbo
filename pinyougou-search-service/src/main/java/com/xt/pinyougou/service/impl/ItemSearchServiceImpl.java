package com.xt.pinyougou.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xt.pinyougou.entity.Item;
import com.xt.pinyougou.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.*;
import org.springframework.data.solr.core.query.result.*;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 搜索服务实现类
 */
@Service(timeout = 7000)
public class ItemSearchServiceImpl implements ItemSearchService {
    @Autowired
    private SolrTemplate solrTemplate;
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public Map<String, Object> search(Map searchMap) {
        Map<String, Object> map = new HashMap<>();
        // 1. 查询列表
        try {
            map.putAll(searchList(searchMap));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // 2. 根据关键字查询商品分类
        List<String> categoryList = searchCategoryList(searchMap);
        map.put("categoryList", categoryList);

        // 3. 查询品牌和规格列表
        String categoryName = (String) searchMap.get("category");
        if (!StringUtils.isEmpty(categoryName)) {
            map.putAll(searchBrandAndSpecList(categoryName));
        } else { // 如果没有分类名称，按照第一个查询
            if (categoryList.size() > 0) {
                map.putAll(searchBrandAndSpecList(categoryList.get(0)));
            }
        }

        return map;
    }

    /**
     * 根据关键字搜索列表
     */
    private Map<String, Object> searchList(Map searchMap) throws UnsupportedEncodingException {
        Map<String, Object> map = new HashMap<>();
        HighlightQuery query = new SimpleHighlightQuery();
        HighlightOptions highlightOptions = new HighlightOptions().addField("item_title"); // 设置高亮域
        highlightOptions.setSimplePrefix("<em style='color:red'>"); //高亮前缀
        highlightOptions.setSimplePostfix("</em>"); // 高亮后缀
        query.setHighlightOptions(highlightOptions); //设置高亮选项

        // 1.1 关键字查询
        Criteria criteria = new Criteria("item_keywords").is(searchMap.get("keywords"));
        query.addCriteria(criteria);

        // 1.2 按分类筛选
        if (!StringUtils.isEmpty(searchMap.get("category"))) {
            Criteria filterCriteria = new Criteria("item_category").is(searchMap.get("category"));
            FilterQuery filterQuery = new SimpleFilterQuery(filterCriteria);
            query.addFilterQuery(filterQuery);
        }

        // 1.3 按品牌过滤
        if (!StringUtils.isEmpty(searchMap.get("brand"))) {
            Criteria filterCriteria = new Criteria("item_brand").is(searchMap.get("brand"));
            FilterQuery filterQuery = new SimpleFilterQuery(filterCriteria);
            query.addFilterQuery(filterQuery);
        }

        // 1.4 规格过滤
        if (null != searchMap.get("spec")) {
            Map<String, String> specMap = (Map<String, String>) searchMap.get("spec");
            for (String key : specMap.keySet()) {
                String k2 = URLEncoder.encode(key, "UTF-8");
                Criteria filterCriteria = new Criteria("item_spec_" + k2).is(specMap.get(key));
                FilterQuery filterQuery = new SimpleFilterQuery(filterCriteria);
                query.addFilterQuery(filterQuery);
            }
        }

        HighlightPage<Item> page = solrTemplate.queryForHighlightPage("pyg_db", query, Item.class);
        for (HighlightEntry<Item> entry : page.getHighlighted()) { //循环高亮入口集合
            Item item = entry.getEntity(); // 获取原实体
            if (entry.getHighlights().size() > 0 && entry.getHighlights().get(0).getSnipplets().size() > 0) {
                item.setTitle(entry.getHighlights().get(0).getSnipplets().get(0)); //设置高亮的结果
            }

        }
        map.put("rows", page.getContent());
        return map;
    }

    /**
     * 查询分类列表
     */
    private List<String> searchCategoryList(Map searchMap) {
        List<String> categoryList = new ArrayList<>();
        Query query = new SimpleQuery();

        // 按关键字查询
        Criteria criteria = new Criteria("item_keywords").is(searchMap.get("keywords"));
        query.addCriteria(criteria);

        /**
         * 设置分组选项
         * Pageable must not be null!
         * GroupOptions：
         * 必须指定 offset/limit，当两个条件都没有时会抛异常
         * 只指定 offset 时，limit 默认为 1
         * 只指定 limit 时，offset 默认为 0
         */
        GroupOptions groupOptions = new GroupOptions().addGroupByField("item_category").setOffset(0).setLimit(10);;
        query.setGroupOptions(groupOptions);
        // 得到分组页
        GroupPage<Item> page = solrTemplate.queryForGroupPage("pyg_db", query, Item.class);
        // 根据列得到分组结果集
        GroupResult<Item> groupResult = page.getGroupResult("item_category");
        // 得到分组结果入口页
        Page<GroupEntry<Item>> groupEntries = groupResult.getGroupEntries();
        // 得到分组入口集合
        List<GroupEntry<Item>> content = groupEntries.getContent();
        for (GroupEntry<Item> entry : content) {
            categoryList.add(entry.getGroupValue()); // 将分组结果的名称封装到返回值中
        }
        return categoryList;
    }

    /**
     * 查询品牌和规格列表
     * @param category 分类名称
     */
    private Map searchBrandAndSpecList(String category){

        Map<String, Object> map = new HashMap<>();

        // 获取模板 ID
        Long templateId = (Long) redisTemplate.boundHashOps("itemCat").get(category);
        if (templateId != null) {
            // 根据模板ID查询品牌列表
            List brandList = (List) redisTemplate.boundHashOps("brandList").get(templateId);
            map.put("brandList", brandList); // 返回值添加品牌列表

            // 根据模板ID查询品牌列表
            List specList = (List) redisTemplate.boundHashOps("specList").get(templateId);
            map.put("specList", specList);
        }

        return map;
    }
}
