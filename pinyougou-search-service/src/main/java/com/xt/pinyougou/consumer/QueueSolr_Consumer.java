package com.xt.pinyougou.consumer;

import com.alibaba.fastjson.JSON;
import com.xt.pinyougou.entity.Item;
import com.xt.pinyougou.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import java.util.Arrays;
import java.util.List;

/**
 * solr 索引库监听器
 */
@Component
public class QueueSolr_Consumer {
    @Autowired
    private ItemSearchService itemSearchService;

    // 导入索引库
    @JmsListener(destination = "${solrQueue}", containerFactory = "queueListenerFactory")
    public void receive(TextMessage textMessage) throws JMSException {
        String text = textMessage.getText();
        System.out.println("**消费者监听收到的消息-solr-import：" + text);

        // 将 json 字符串转为商品列表
        List<Item> itemList = JSON.parseArray(text, Item.class);
        itemSearchService.importList(itemList);
    }

    // 删除索引库
    @JmsListener(destination = "${solrQueueDelete}", containerFactory = "queueListenerFactory")
    public void receive(ObjectMessage objectMessage) throws JMSException {
        Long[] goodsIds = (Long[]) objectMessage.getObject();
        System.out.println("**消费者监听收到的消息-solr-delete：" + goodsIds);

        itemSearchService.deleteByGoodsIds(Arrays.asList(goodsIds));
        System.out.println("成功删除索引库中的记录");
    }
}
