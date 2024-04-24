package com.weixf.utils.cache;

import lombok.Getter;

@Getter
public class CacheEntity {

    /**
     * 缓存键
     */
    private String key;

    /**
     * 缓存值
     */
    private Object value;

    /**
     * 过期时间
     */
    private Long expireTime;


    public CacheEntity setKey(String key) {
        this.key = key;
        return this;
    }

    public CacheEntity setValue(Object value) {
        this.value = value;
        return this;
    }

    public CacheEntity setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
        return this;
    }
}
