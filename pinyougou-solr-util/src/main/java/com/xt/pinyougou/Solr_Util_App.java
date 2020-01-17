package com.xt.pinyougou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.xt.pinyougou.mapper"})
public class Solr_Util_App {
    public static void main(String[] args) {
        SpringApplication.run(Solr_Util_App.class, args);
    }
}
