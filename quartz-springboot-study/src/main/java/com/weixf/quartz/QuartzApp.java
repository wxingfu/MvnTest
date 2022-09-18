package com.weixf.quartz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.weixf.quartz.mapper")
@SpringBootApplication
public class QuartzApp {

    public static void main(String[] args) {
        SpringApplication.run(QuartzApp.class, args);
    }
}
