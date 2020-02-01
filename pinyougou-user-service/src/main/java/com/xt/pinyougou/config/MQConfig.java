package com.xt.pinyougou.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;

@Configuration
@EnableJms
public class MQConfig {

    @Value("${sms}")
    private String sms;

    @Bean
    public Queue smsQueue() {
        return new ActiveMQQueue(sms);
    }
}
