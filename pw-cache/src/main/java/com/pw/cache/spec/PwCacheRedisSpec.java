package com.pw.cache.spec;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import com.pw.cache.PwCacheApi;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class PwCacheRedisSpec<T> implements PwCacheApi<T> {

    private final RedisTemplate<String, T> redisTemplate;

    private final String prefix;

    private final Collection<String> KEYS = CollectionUtil.newArrayList();

    public PwCacheRedisSpec(String prefix, RedisTemplate<String, T> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.prefix = prefix;
    }

    @Override
    public void init() {

    }

    @Override
    public String prefix() {
        return this.prefix;
    }

    @Override
    public void set(String key, T value) {
        redisTemplate.boundValueOps(prefix() + key).set(value);
        KEYS.add(key);
    }

    @Override
    public void set(String key, T value, long timeout) {
        redisTemplate.boundValueOps(prefix() + key).set(value, timeout, TimeUnit.MILLISECONDS);
        KEYS.add(key);
    }

    @Override
    public T get(String key) {
        return redisTemplate.boundValueOps(prefix() + key).get();
    }

    @Override
    public void remove(String... keys) {
        redisTemplate.delete(Arrays.stream(keys).map(item -> prefix() + item).toList());
        KEYS.removeAll(Arrays.stream(keys).toList());
    }

    @Override
    public void clear() {
        remove(keys().toArray(String[]::new));
    }

    @Override
    public void expire(String key, long timeout) {
        redisTemplate.boundValueOps(prefix() + key).expire(timeout, TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean contains(String key) {
        T value = get(key);
        return value != null;
    }

    @Override
    public Collection<String> keys() {
        return CollectionUtil.isEmpty(KEYS) ? null : KEYS;
    }

    @Override
    public Map<String, T> all() {
        Collection<String> keys = keys();
        HashMap<String, T> map = MapUtil.newHashMap();
        for(String key: keys){
            map.put(key, get(key));
        }
        return map;
    }
}
