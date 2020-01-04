package com.xt.pinyougou;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableTransactionManagement
@SpringBootApplication
@EnableSwagger2
@EnableDubbo
@MapperScan(basePackages = {"com.xt.pinyougou.mapper"})
public class Provider_Sellergoods_App {

    public static void main(String[] args) {
        SpringApplication.run(Provider_Sellergoods_App.class, args);
    }
}
