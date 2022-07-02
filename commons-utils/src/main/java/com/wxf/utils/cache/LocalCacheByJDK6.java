package com.wxf.utils.cache;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class LocalCacheByJDK6 {

    /**
     * 默认缓存时长 单位s
     */
    private static final int DEFAULT_TIMEOUT = 3600;

    /**
     * 默认缓存容量
     */
    private static final int DEFAULT_SIZE = 1000;

    /**
     * 存储数据
     */
    private static final Map<String, Object> data;

    /**
     * 定时器  用来控制 缓存的超时时间
     * private static Timer timer;
     * timer = new Timer();
     * timer.schedule(CacheCleanTask.cacheTask(key),DEFAULT_TIMEOUT);
     * <p>
     * 1)多线程并行处理定时任务时，Timer运行多个TimeTask时，只要其中之一没有捕获抛出的异常，
     * 其它任务便会自动终止运行，使用ScheduledExecutorService则没有这个问题
     * 2)Timer内部是一个线程，任务1所需的时间超过了两个任务间的间隔时会导致问题
     * 3)Timer执行周期任务时依赖系统时间
     */

    private static final ScheduledExecutorService executorService;

    // 初始化
    static {
        data = new ConcurrentHashMap<String, Object>(DEFAULT_SIZE);
        executorService = new ScheduledThreadPoolExecutor(2);
    }

    /**
     * 私有化构造函数
     */
    private LocalCacheByJDK6() {
    }

    /**
     * 增加缓存 默认有效时长
     */
    public static void put(final String key, Object value) {
        data.put(key, value);
        // 定时器 调度任务，用于根据 时间 定时清除 对应key 缓存
        executorService.schedule(new TimerTask() {
            @Override
            public void run() {
                remove(key);
            }
        }, DEFAULT_TIMEOUT, TimeUnit.SECONDS);
    }


    /**
     * 增加缓存  并设置缓存时长 单位 s
     *
     * @param timeout 缓存时长 单位s
     */
    public static void put(final String key, Object value, int timeout) {
        data.put(key, value);
        //lambda 替换匿名内部类
        executorService.schedule(new TimerTask() {
            @Override
            public void run() {
                remove(key);
            }
        }, timeout, TimeUnit.SECONDS);
    }

    /**
     * 增加缓存 并指定过期时间点
     *
     * @param expire 指定过期时间点
     */
    public static void put(final String key, Object value, Date expire) {
        data.put(key, value);
        Date now = new Date();
        if (now.before(expire)) {
            return;
        }
        long expireTime = expire.getTime();
        long nowTime = now.getTime();
        int seconds = (int) ((expireTime - nowTime) / 1000);
        executorService.schedule(new TimerTask() {
            @Override
            public void run() {
                remove(key);
            }
        }, seconds, TimeUnit.SECONDS);
    }

    /**
     * 批量增加缓存
     */
    public static void put(Map<String, Object> cache) {
        if (!(cache == null || cache.isEmpty())) {
            for (Map.Entry<String, Object> entry : cache.entrySet()) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    public static void put(Map<String, Object> cache, int timeout) {
        if (!(cache == null || cache.isEmpty())) {
            for (Map.Entry<String, Object> entry : cache.entrySet()) {
                put(entry.getKey(), entry.getValue(), timeout);
            }
        }
    }

    public static void put(Map<String, Object> cache, Date expireTime) {
        if (!(cache == null || cache.isEmpty())) {
            for (Map.Entry<String, Object> entry : cache.entrySet()) {
                put(entry.getKey(), entry.getValue(), expireTime);
            }
        }
    }

    /**
     * 获取缓存
     */
    public static Object get(String key) {
        return data.get(key);
    }

    /**
     * 获取当前缓存中 所有的key
     */
    public static Set<String> cacheKeys() {
        return data.keySet();
    }

    public static Map<String, Object> allCache() {
        return data;
    }

    /**
     * 判断缓存是否包含key
     */
    public boolean containKey(String key) {
        return data.containsKey(key);
    }

    /**
     * 获取当前缓存大小
     */
    public static int size() {
        return data.size();
    }

    /**
     * 删除缓存
     */
    public static void remove(String key) {
        data.remove(key);
    }

    /**
     * 清空所有缓存
     */
    public static void clear() {
        if (size() > 0) {
            data.clear();
        }
    }
}