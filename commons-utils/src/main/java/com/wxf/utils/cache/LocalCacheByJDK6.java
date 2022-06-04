package com.wxf.utils.cache;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
 *
 * @author weixf
 * @date 2022-05-31
 */
public class LocalCacheByJDK6 {

    /**
     * Ĭ�ϻ���ʱ�� ��λs
     */
    private static final int DEFAULT_TIMEOUT = 3600;

    /**
     * Ĭ�ϻ�������
     */
    private static final int DEFAULT_SIZE = 1000;

    /**
     * �洢����
     */
    private static final Map<String, Object> data;

    /**
     * ��ʱ��  �������� ����ĳ�ʱʱ��
     * private static Timer timer;
     * timer = new Timer();
     * timer.schedule(CacheCleanTask.cacheTask(key),DEFAULT_TIMEOUT);
     * <p>
     * 1)���̲߳��д���ʱ����ʱ��Timer���ж��TimeTaskʱ��ֻҪ����֮һû�в����׳����쳣��
     * �����������Զ���ֹ���У�ʹ��ScheduledExecutorService��û���������
     * 2)Timer�ڲ���һ���̣߳�����1�����ʱ�䳬�������������ļ��ʱ�ᵼ������
     * 3)Timerִ����������ʱ����ϵͳʱ��
     */

    private static final ScheduledExecutorService executorService;

    // ��ʼ��
    static {
        data = new ConcurrentHashMap<String, Object>(DEFAULT_SIZE);
        executorService = new ScheduledThreadPoolExecutor(2);
    }

    /**
     * ˽�л����캯��
     */
    private LocalCacheByJDK6() {
    }

    /**
     * ���ӻ��� Ĭ����Чʱ��
     */
    public static void put(final String key, Object value) {
        data.put(key, value);
        // ��ʱ�� �����������ڸ��� ʱ�� ��ʱ��� ��Ӧkey ����
        executorService.schedule(new TimerTask() {
            @Override
            public void run() {
                remove(key);
            }
        }, DEFAULT_TIMEOUT, TimeUnit.SECONDS);
    }


    /**
     * ���ӻ���  �����û���ʱ�� ��λ s
     *
     * @param timeout ����ʱ�� ��λs
     */
    public static void put(final String key, Object value, int timeout) {
        data.put(key, value);
        //lambda �滻�����ڲ���
        executorService.schedule(new TimerTask() {
            @Override
            public void run() {
                remove(key);
            }
        }, timeout, TimeUnit.SECONDS);
    }

    /**
     * ���ӻ��� ��ָ������ʱ���
     *
     * @param expire ָ������ʱ���
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
     * �������ӻ���
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
     * ��ȡ����
     */
    public static Object get(String key) {
        return data.get(key);
    }

    /**
     * ��ȡ��ǰ������ ���е�key
     */
    public static Set<String> cacheKeys() {
        return data.keySet();
    }

    public static Map<String, Object> allCache() {
        return data;
    }

    /**
     * �жϻ����Ƿ����key
     */
    public boolean containKey(String key) {
        return data.containsKey(key);
    }

    /**
     * ��ȡ��ǰ�����С
     */
    public static int size() {
        return data.size();
    }

    /**
     * ɾ������
     */
    public static void remove(String key) {
        data.remove(key);
    }

    /**
     * ������л���
     */
    public static void clear() {
        if (size() > 0) {
            data.clear();
        }
    }
}
