package com.wxf.spring.controller;

import com.wxf.spring.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/thread")
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    @GetMapping("/async")
    public void async() {
        asyncService.executeAsync();
    }
}
