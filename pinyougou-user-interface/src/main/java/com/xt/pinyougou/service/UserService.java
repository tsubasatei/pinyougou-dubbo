package com.xt.pinyougou.service;

import com.xt.pinyougou.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author xt
 * @since 2020-01-30
 */
public interface UserService extends IService<User> {

    /**
     * 添加用户
     * @param user
     * @return
     */
    boolean add(User user);

    /**
     * 生成短信验证码
     */
    void createSmsCode(String phone);

    /**
     * 判断短信验证码是否存在
     * @param phone
     * @return
     */
    boolean  checkSmsCode(String phone, String code);

}
