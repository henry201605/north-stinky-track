package com.nstepa.wc.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages = "com.nstepa.wc")
@EnableWebMvc
@MapperScan("com.nstepa.wc.mybatis.support.mappers")
@EnableTransactionManagement
@EnableAsync
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
