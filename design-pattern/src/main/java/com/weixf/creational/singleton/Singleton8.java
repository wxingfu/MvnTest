package com.weixf.creational.singleton;

/**
 * 8.枚举【推荐使用】
 * 借助JDK1.5中添加的枚举来实现单例模式。
 * 不仅能避免多线程同步问题，而且还能防止反序列化重新创建新的对象。
 * <p>
 * Singleton8.INSTANCE.getInstance()
 */
public enum Singleton8 {
    INSTANCE
}
