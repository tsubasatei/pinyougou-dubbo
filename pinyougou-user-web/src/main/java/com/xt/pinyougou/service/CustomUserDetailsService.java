package com.xt.pinyougou.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 用于加载用户信息 实现UserDetailsService接口，或者实现AuthenticationUserDetailsService接口
 */
@Service
public class CustomUserDetailsService implements AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {

	private static final Logger USER_SERVICE_LOGGER = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Autowired
	private UserDetailServiceImpl userDetailService;

    @Override
    public UserDetails loadUserDetails(CasAssertionAuthenticationToken token) throws UsernameNotFoundException {
    	// 结合具体的逻辑去实现用户认证，并返回继承UserDetails的用户对象;
		System.out.println("当前的用户名是：" + token.getName());

		USER_SERVICE_LOGGER.info("校验成功的登录名为: " + token.getName());
		//此处涉及到数据库操作然后读取权限集合，读者可自行实现
		UserDetails userDetails = userDetailService.loadUserByUsername(token.getName());
		return userDetails;
    }
 
}