package com.xt.pinyougou.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xt.pinyougou.entity.Seller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 认证类
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Reference
    private SellerService sellerService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("登录的用户名是：" + username);
        Seller seller = sellerService.getById(username);
        if (seller != null && seller.getStatus().equals("1")) {
            String password = seller.getPassword();
            logger.info("数据库的密码是：" + password);

            // AuthorityUtils.commaSeparatedStringToAuthorityList("admin")
            // 构建角色列表
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_SELLER"));
            return new User(username, password, authorities);
        }

        return null;
    }
}
