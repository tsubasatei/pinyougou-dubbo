package com.xt.pinyougou.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.xt.pinyougou.entity.User;
import com.xt.pinyougou.mapper.UserMapper;
import com.xt.pinyougou.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.jms.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author xt
 * @since 2020-01-30
 */
@Service(timeout = 5000)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * 签名类容
     * 签名参数使用的是`签名内容`，而不是`签名ID`。这里的签名"腾讯云"只是示例，真实的签名需要在短信控制台申请
     */
    @Value("${tengxunSms.signName}")
    private String signName;

    /**
     * 模板ID--也可以自己写
     */
    @Value("${tengxunSms.templateId}")
    private Integer templateId;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Queue smsQueue;

    @Transactional
    @Override
    public boolean add(User user) {
        try {
            // 创建时间
            user.setCreated(LocalDateTime.now());
            // 修改时间
            user.setUpdated(LocalDateTime.now());

            //对密码加密
            String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
            user.setPassword(password);

            int insert = baseMapper.insert(user);
            return insert == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public  void createSmsCode(String phone) {
        // 1.生成6位随机数
        String code =  (long) (Math.random() * 1000000) + "";
        System.out.println("验证码：" + code);

        // 2.存入缓存
        redisTemplate.boundHashOps("smsCode").put(phone, code);

        // 3.发送到activeMQ	....
        Map<String, String> map = new HashMap<>();
        map.put("signName", signName);
        map.put("templateId", templateId + "");
        map.put("phone", phone);
        Map m = new HashMap<>();
        m.put("number", code);
        map.put("params", JSON.toJSONString(m));//参数

        jmsMessagingTemplate.convertAndSend(smsQueue, map);


    }

    /**
     * 判断验证码是否正确
     * @param phone
     * @param code
     * @return
     */
    @Override
    public boolean checkSmsCode(String phone, String code) {
        // 得到缓存中存储的验证码
        String smsCode = (String) redisTemplate.boundHashOps("smsCode").get(phone);
        if (smsCode == null) {
            return false;
        }
        if (!smsCode.equals(code)) {
            return false;
        }
        return true;
    }
}
