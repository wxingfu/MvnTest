package com.wxf.encrypt.starter.controller;

import com.wxf.encrypt.starter.anno.Decrypt;
import com.wxf.encrypt.starter.anno.Encrypt;
import com.wxf.encrypt.starter.entity.User;
import com.wxf.encrypt.starter.entity.vo.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/*
 *
 * @author weixf
 * @date 2022-06-17
 */
@RestController
public class HelloController {
    @GetMapping("/user")
    @Encrypt
    public RespBean getUser() {
        User user = new User();
        user.setId((long) 99);
        user.setUsername("javaboy");
        return RespBean.ok("ok", user);
    }

    @PostMapping("/user")
    public RespBean addUser(@RequestBody @Decrypt User user) {
        System.out.println("user = " + user);
        return RespBean.ok("ok", user);
    }
}
