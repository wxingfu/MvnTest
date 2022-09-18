package com.weixf.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"com.weixf.security.mapper"})
@SpringBootApplication
public class SecurityDemoApp {

    public static void main(String[] args) {
        SpringApplication.run(SecurityDemoApp.class, args);
    }

}