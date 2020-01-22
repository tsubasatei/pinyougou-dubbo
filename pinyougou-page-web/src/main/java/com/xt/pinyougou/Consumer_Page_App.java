package com.xt.pinyougou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class Consumer_Page_App {

    public static void main(String[] args) {
        SpringApplication.run(Consumer_Page_App.class, args);
    }
}
