package com.xt.pinyougou;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableSwagger2
@EnableDubbo
@EnableTransactionManagement
public class Consumer_Portal_App {

    public static void main(String[] args) {
        SpringApplication.run(Consumer_Portal_App.class, args);
    }
}
