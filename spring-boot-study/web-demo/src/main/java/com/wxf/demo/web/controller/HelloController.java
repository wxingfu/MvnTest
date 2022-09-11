package com.wxf.demo.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wxf.demo.web.util.AesUtil;
import com.wxf.demo.web.util.Md5Util;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

/*
 *
 * @author weixf
 * @date 2022-07-04
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @PostMapping(value = "/test")
    public String test(@RequestBody String param) {

        System.out.println("入参：" + param);
        JSONObject jsonObject = JSON.parseObject(param);
        String data = (String) jsonObject.get("data");
        String decrypt = AesUtil.decrypt(data, "ABCDEF");
        byte[] decode = Base64.getDecoder().decode(decrypt);
        String out = new String(decode);
        System.out.println("解密后：" + out);

        String base64String = Base64.getEncoder().encodeToString(out.getBytes());
        String aesString = AesUtil.encrypt(base64String, "ABCDEF");
        String signString = Md5Util.Md5(base64String + "123456");
        String params = "{\"data\":\"" + aesString + "\",\"sign\":\"" + signString + "\"}";
        System.out.println("出参：" + params);

        JSONObject parseObject = JSONObject.parseObject(params);
        System.out.println(parseObject);
        data = (String) jsonObject.get("data");
        decrypt = AesUtil.decrypt(data, "ABCDEF");
        decode = Base64.getDecoder().decode(decrypt);
        out = new String(decode);
        System.out.println("解密后：" + out);

        return params;
    }

    @GetMapping("/test2")
    public String test2() {
        return "param";
    }
}
