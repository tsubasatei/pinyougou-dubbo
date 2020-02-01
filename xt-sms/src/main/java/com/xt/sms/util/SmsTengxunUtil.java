package com.xt.sms.util;

import com.alibaba.fastjson.JSON;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


/**
 * 短信服务工具类
 */
@Component
public class SmsTengxunUtil {
    /**
     * appID
     */
    @Value("${tengxunSms.appId}")
    private Integer appId;

    /**
     * 密钥
     */
    @Value("${tengxunSms.appKey}")
    private String appKey;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 指定模板 ID 单发短信
     * @param signName: 签名名称
     * @param templateId: 模板ID
     * @param phone: 需要发送短信的手机号码
     * @param params: 需要发送的参数
     *
     *              HTTPException: HTTP 响应码错误
     *              JSONException: JSON 解析错误
     *              IOException: 网络 IO 错误
     */
    public String sendSms(String signName, String templateId, String phone, String params) {
        String status = "";
        try {
            ArrayList<String> paramList = (ArrayList<String>) JSON.parseArray(params, String.class);
            SmsSingleSender ssender = new SmsSingleSender(appId, appKey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phone, Integer.parseInt(templateId), paramList, signName, "", "");
            System.out.println(result);
            status = result.errMsg;
            logger.info("sms send status, template id [{}], phone is [{}], status is [{}] ", templateId, phone, status);
        } catch (Exception e){
            logger.info("sms send status, template id [{}], phone is [{}], status is [{}] ", templateId, phone, status, e);
        }
        return status;  // 此处的status只有发送成功是"OK"
    }
}