package com.xt.sms.listener;

import com.xt.sms.util.SmsTengxunUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 消息监听类
 */
@Component
public class SmsListener {

    @Autowired
    private SmsTengxunUtil smsTengxunUtil;

    @JmsListener(destination = "sms")
    public void sendSms(Map<String, String> map) {
        smsTengxunUtil.sendSms(
                map.get("signName"),
                map.get("templateId"),
                map.get("phone"),
                map.get("params")
        );

    }
}
