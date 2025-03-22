package com.pw.cache;

import java.util.Collection;
import java.util.Map;

public interface PwCacheApi<T> {

    void init();


    /**
     * 设置前缀
     * @return
     */
    String prefix();

    /**
     * 设置缓存
     * @param key
     * @param value
     */
    void set(String key, T value);

    /**
     * 设置缓存，且有过期时间
     * @param key
     * @param value
     * @param timeout
     */
    void set(String key, T value, long timeout);

    /**
     * 获取缓存
     * @param key
     * @return
     */
    T get(String key);

    /**
     * 删除缓存
     * @param keys
     */
    void remove(String... keys);

    /**
     * 清空缓存
     */
    void clear();

    /**
     * 设置过期时间
     * @param key
     * @param timeout
     */
    void expire(String key, long timeout);

    /**
     * 是否存在key
     * @param key
     * @return
     */
    boolean contains(String key);

    /**
     * 获取所有的key
     * @return
     */
    Collection<String> keys();

    /**
     * 获取所有的缓存
     * @return
     */
    Map<String, T> all();

}
