package com.weixf.shiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUser {

    private Integer id;
    private String name;
    private String pwd;
    private Integer rid;
}
