package com.weixf.security.common;

import com.weixf.security.security.entity.SecurityUser;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Security工具类
 */
public class SecurityUtil {

    /**
     * 私有化构造器
     */
    private SecurityUtil() {
    }

    /**
     * 获取当前用户信息
     */
    public static SecurityUser getUserInfo() {
        return (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * 获取当前用户ID
     */
    public static Long getUserId() {
        return getUserInfo().getUserId();
    }

    /**
     * 获取当前用户账号
     */
    public static String getUserName() {
        return getUserInfo().getUsername();
    }
}