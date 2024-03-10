package com.weixf.dynamic.datasource.service;


import com.weixf.dynamic.datasource.annotion.DataSource;
import com.weixf.dynamic.datasource.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @DataSource("master")
    public Integer master() {
        return userMapper.count();
    }

    @DataSource("slave")
    public Integer slave() {
        return userMapper.count();
    }
}
