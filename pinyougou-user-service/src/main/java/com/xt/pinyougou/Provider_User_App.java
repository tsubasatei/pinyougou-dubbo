package com.xt.pinyougou;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
@EnableDubbo
@MapperScan("com.xt.pinyougou.mapper")
public class Provider_User_App {

    public static void main(String[] args) {
        SpringApplication.run(Provider_User_App.class, args);
    }
}
