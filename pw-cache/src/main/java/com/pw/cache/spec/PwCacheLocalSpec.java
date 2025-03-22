package com.pw.cache.spec;

import cn.hutool.cache.impl.CacheObj;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import com.pw.cache.PwCacheApi;

import java.util.*;

public class PwCacheLocalSpec<T> implements PwCacheApi<T> {

    private final TimedCache<String, T> CACHE;

    private final String prefix;

    public PwCacheLocalSpec(String prefix, TimedCache<String, T> cache) {
        this.CACHE = cache;
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
        CACHE.put(prefix()+key, value);
    }

    @Override
    public void set(String key, T value, long timeout) {
        CACHE.put(prefix()+key, value, timeout);
    }

    @Override
    public T get(String key) {
        return CACHE.get(prefix()+key);
    }

    @Override
    public void remove(String... keys) {
        for (String key : keys) {
            CACHE.remove(prefix()+key);
        }
    }

    @Override
    public void clear() {
        CACHE.clear();
    }

    @Override
    public void expire(String key, long timeout) {
        CACHE.put(prefix()+key, CACHE.get(prefix()+key), timeout);
    }

    @Override
    public boolean contains(String key) {
        return CACHE.containsKey(prefix()+key);
    }

    @Override
    public Collection<String> keys() {
        Iterator<CacheObj<String, T>> iterator = CACHE.cacheObjIterator();
        Collection<String> collection = CollectionUtil.newArrayList();
        while (iterator.hasNext()) {
            String key = iterator.next().getKey();
            if(key.startsWith(prefix())) {
                collection.add(key.substring(prefix().length()));
            }else{
                collection.add(key);
            }
        }
        return collection;
    }

    @Override
    public Map<String, T> all() {
        Collection<String> keys = keys();
        HashMap<String, T> map = MapUtil.newHashMap();
        for(String key: keys){
            map.put(prefix()+key, CACHE.get(prefix()+key));
        }
        return map;
    }

}
