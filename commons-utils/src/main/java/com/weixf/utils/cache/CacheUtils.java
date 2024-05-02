package com.weixf.utils.cache;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CacheUtils {

    /**
     * 缓存数据
     */
    private final static Map<String, CacheEntity> CACHE_MAP = new ConcurrentHashMap<>();

    /**
     * 定时器线程池，用于清除过期缓存
     */
    private static final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();


    static {
        // 注册一个定时线程任务，服务启动1秒之后，每隔500毫秒执行一次
        // 清理过期缓存
        executor.scheduleAtFixedRate(CacheUtils::clearCache, 1000, 500, TimeUnit.MILLISECONDS);
    }

    /**
     * 添加缓存
     *
     * @param key   缓存键
     * @param value 缓存值
     */
    public static void put(String key, Object value) {
        put(key, value, 0);
    }


    /**
     * 添加缓存
     *
     * @param key    缓存键
     * @param value  缓存值
     * @param expire 缓存时间，单位秒
     */
    public static void put(String key, Object value, long expire) {
        CacheEntity cacheEntity = new CacheEntity().setKey(key).setValue(value);
        if (expire > 0) {
            Long expireTime = System.currentTimeMillis() + Duration.ofSeconds(expire).toMillis();
            cacheEntity.setExpireTime(expireTime);
        }
        CACHE_MAP.put(key, cacheEntity);
    }


    /**
     * 获取缓存
     *
     * @param key key
     */
    public static Object get(String key) {
        if (CACHE_MAP.containsKey(key)) {
            return CACHE_MAP.get(key).getValue();
        }
        return null;
    }

    /**
     * 移除缓存
     *
     * @param key key
     */
    public static void remove(String key) {
        CACHE_MAP.remove(key);
    }

    /**
     * 清理过期的缓存数据
     */
    private static void clearCache() {
        if (CACHE_MAP.isEmpty()) {
            return;
        }
        CACHE_MAP.entrySet().removeIf(entry -> entry.getValue().getExpireTime() != null && entry.getValue().getExpireTime() > System.currentTimeMillis());
    }

}
