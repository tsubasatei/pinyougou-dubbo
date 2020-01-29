package com.xt.pinyougou.consumer;

import com.xt.pinyougou.page.service.ItemPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

/**
 * 静态页面 监听器
 */
@Component
public class TopicPage_Consumer {
    @Autowired
    private ItemPageService itemPageService;

    @JmsListener(destination = "${pageTopic}", containerFactory = "topicListenerFactory")
    public void receive(ObjectMessage objectMessage) throws JMSException {
        Long goodsId = (Long) objectMessage.getObject();
        System.out.println("**消费者监听收到的消息-page-gen：" + goodsId);

        boolean b = itemPageService.genItemHtml(goodsId);
        System.out.println("静态页面生成 " + b);
    }

    @JmsListener(destination = "${pageTopicDelete}", containerFactory = "topicListenerFactory")
    public void receiveDelete(ObjectMessage objectMessage) throws JMSException {
        Long[] goodsIds = (Long[]) objectMessage.getObject();
        System.out.println("**消费者监听收到的消息-page-delete：" + goodsIds);

        boolean b = itemPageService.deleteItemHtml(goodsIds);
        System.out.println("网页删除结果：" + b);
    }
}
