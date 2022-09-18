package com.weixf.security.security.handler;


import com.weixf.security.common.JWTTokenUtil;
import com.weixf.security.common.ResultUtil;
import com.weixf.security.common.config.JWTConfig;
import com.weixf.security.security.entity.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录成功处理类
 */
@Slf4j
@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {
    /**
     * 登录成功返回结果
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 组装JWT
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        String token = JWTTokenUtil.createAccessToken(securityUser);
        token = JWTConfig.tokenPrefix + token;
        // 封装返回参数
        Map<String, Object> resultData = new HashMap<>();
        resultData.put("code", "200");
        resultData.put("msg", "登录成功");
        resultData.put("token", token);
        ResultUtil.responseJson(response, resultData);
    }
}