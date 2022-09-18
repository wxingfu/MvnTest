package com.weixf.security.controller;


import com.weixf.security.common.ResultUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/index")
public class IndexController {


    /**
     * 首页
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public Map<String, Object> userLogin() {
        // 组装参数
        Map<String, Object> result = new HashMap<>();
        result.put("title", "这里是首页不需要权限和登录拦截");
        return ResultUtil.resultSuccess(result);
    }

}
