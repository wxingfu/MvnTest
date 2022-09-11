package com.wxf.dynamic.datasource.service;


import com.wxf.dynamic.datasource.annotion.DataSource;
import com.wxf.dynamic.datasource.mapper.UserMapper;
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
