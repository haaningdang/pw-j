package com.pw.cache.spec;

import cn.hutool.cache.impl.CacheObj;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import com.pw.cache.PwCacheApi;

import java.util.*;

public class PwCacheLocalSpec<T> implements PwCacheApi<T> {

    private final TimedCache<String, T> CACHE;

    public PwCacheLocalSpec(TimedCache<String, T> cache) {
        CACHE = cache;
    }

    @Override
    public void init() {

    }

    @Override
    public void set(String key, T value) {
        CACHE.put(key, value);
    }

    @Override
    public void set(String key, T value, long timeout) {
        CACHE.put(key, value, timeout);
    }

    @Override
    public T get(String key) {
        return CACHE.get(key);
    }

    @Override
    public void remove(String... keys) {
        for (String key : keys) {
            CACHE.remove(key);
        }
    }

    @Override
    public void clear() {
        CACHE.clear();
    }

    @Override
    public void expire(String key, long timeout) {
        CACHE.put(key, CACHE.get(key), timeout);
    }

    @Override
    public boolean contains(String key) {
        return CACHE.containsKey(key);
    }

    @Override
    public Collection<String> keys() {
        Iterator<CacheObj<String, T>> iterator = CACHE.cacheObjIterator();
        Collection<String> collection = CollectionUtil.newArrayList();
        while (iterator.hasNext()) {
            collection.add(iterator.next().getKey());
        }
        return collection;
    }

    @Override
    public Map<String, T> all() {
        Collection<String> keys = keys();
        HashMap<String, T> map = MapUtil.newHashMap();
        for(String key: keys){
            map.put(key, CACHE.get(key));
        }
        return map;
    }

}
