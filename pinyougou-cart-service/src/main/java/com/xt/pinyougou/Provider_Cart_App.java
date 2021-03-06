package com.xt.pinyougou;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableCaching
@EnableTransactionManagement
@SpringBootApplication
@EnableSwagger2
@EnableDubbo
@MapperScan(basePackages = {"com.xt.pinyougou.mapper"})
public class Provider_Cart_App {

    public static void main(String[] args) {
        SpringApplication.run(Provider_Cart_App.class, args);
    }
}
