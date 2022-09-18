package com.weixf.security.common;

import com.alibaba.fastjson.JSON;
import com.weixf.security.common.config.JWTConfig;
import com.weixf.security.security.entity.SecurityUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class JWTTokenUtil {

    /**
     * 生成Token
     */
    public static String createAccessToken(SecurityUser securityUser) {
        // 登陆成功生成JWT
        return Jwts.builder()
                // 放入用户名和用户ID
                .setId(securityUser.getUserId() + "")
                // 主题
                .setSubject(securityUser.getUsername())
                // 签发时间
                .setIssuedAt(new Date())
                // 签发者
                .setIssuer("weixf")
                // 自定义属性 放入用户拥有权限
                .claim("authorities", JSON.toJSONString(securityUser.getAuthorities()))
                // 失效时间
                .setExpiration(new Date(System.currentTimeMillis() + JWTConfig.expiration))
                // 签名算法和密钥
                .signWith(SignatureAlgorithm.HS512, JWTConfig.secret)
                .compact();
    }
}
