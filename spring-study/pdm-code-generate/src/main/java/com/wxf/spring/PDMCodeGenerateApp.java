package com.wxf.spring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.wxf.spring.mapper")
@SpringBootApplication
public class PDMCodeGenerateApp {

    public static void main(String[] args) {
        SpringApplication.run(PDMCodeGenerateApp.class, args);
    }
}
