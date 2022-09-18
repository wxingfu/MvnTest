package com.weixf.quartz.entity;

import lombok.Data;

@Data
public class Person {
    private Long id;
    private String name;
    private Integer age;
    private String sex;
    private String address;
    private String sect;
    private String skill;
    private Integer power;
    private String createTime;
    private String modifyTime;
}
