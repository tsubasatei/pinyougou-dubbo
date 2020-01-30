package com.xt.pinyougou.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * ActiveMQ 的配置
 */
@Configuration
@EnableJms
public class MyMQConfig {

    @Value("${solrQueue}")
    private String solrQueue;

    @Value("${solrQueueDelete}")
    private String solrQueueDelete;

    @Value("${pageTopic}")
    private String pageTopic;

    @Value("${pageTopicDelete}")
    private String pageTopicDelete;

    /**
     * 用于发送solr导入的消息
     */
    @Bean
    public Queue queueSolr() {
        return new ActiveMQQueue(solrQueue);
    }

    /**
     * 用于发送solr移除索引库记录的消息
     */
    @Bean
    public Queue queueSolrDelete() {
        return new ActiveMQQueue(solrQueueDelete);
    }

    /**
     * 用于发送 创建静态页面 的消息
     */
    @Bean
    public Topic topicPage() {
        return new ActiveMQTopic(pageTopic);
    }

    /**
     * 用于发送 删除静态页面 的消息
     */
    @Bean
    public Topic topicPageDelete() {
        return new ActiveMQTopic(pageTopicDelete);
    }

}
