//package com.xt.pinyougou.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
///**
// * Security 配置
// */
//@EnableWebSecurity
//public class ManagerSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    // 加密算法
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    /**
//     * 页面拦截规则
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                // 设置静态的资源允许所有访问
//                .antMatchers("/webjars/**", "/static/**", "/", "/login").permitAll()
//                // 其他所有资源都需要登录后才能访问
//                .anyRequest().authenticated();
//
//        http.formLogin()
//                // 设置默认登录页面路径 /login
//                .loginPage("/login")
//                // 强制指定登录成功后跳转的路径(如果登录前有默认登录路径的话登录成功后依旧跳转为登录前的路径)
//                .defaultSuccessUrl("/admin/index")
//            .and()
//            // 退出
//            .logout().logoutUrl("/logout").logoutSuccessUrl("/login");
//
//        /**
//         * IFrame 出 Refused to display 'URL' in a frame because it set 'X-Frame-Options' to 'DENY'
//         */
//        http.headers().frameOptions().sameOrigin();
//
//        // 退出
//        http.logout().logoutUrl("/logout").logoutSuccessUrl("/login");
//
//        // 关闭跨域
//        http.csrf().disable();
//    }
//
//    /**
//     * 认证规则
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password(passwordEncoder().encode("123456"))
//                .roles("admin");
//    }
//}
