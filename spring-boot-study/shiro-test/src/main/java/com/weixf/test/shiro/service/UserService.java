package com.weixf.test.shiro.service;


import com.weixf.test.shiro.dao.UserDao;
import com.weixf.test.shiro.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User findByName(String name) {
        return this.userDao.findByUsername(name);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }
}
