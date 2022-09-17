package com.wxf.demo.web.controller;

import com.wxf.demo.web.aop.Prevent;
import com.wxf.demo.web.common.Response;
import com.wxf.demo.web.dto.TestRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 切面实现入参校验
 */
@RestController
public class PreventController {

    /**
     * 测试防刷
     */
    @ResponseBody
    @GetMapping(value = "/testPrevent")
    @Prevent
    public Response testPrevent(TestRequest request) {
        return Response.success("调用成功");
    }


    /**
     * 测试防刷
     */
    @ResponseBody
    @GetMapping(value = "/testPreventIncludeMessage")
    @Prevent(message = "10秒内不允许重复调多次", value = "10")
    public Response testPreventIncludeMessage(TestRequest request) {
        return Response.success("调用成功");
    }

}
