package com.fengxudong.framework.cache;


import com.fengxudong.framework.context.FrameworkContextHolder;

import java.util.concurrent.TimeUnit;

public class FrameworkCache implements IFrameworkCache {

    private ICache cache;

    public FrameworkCache(ICache cache){
        this.cache = cache;
    }

    @Override
    public void set(String key, Object value, long timeout, TimeUnit timeUnit) {
        cache.set(cacheKey(key), value, timeout, timeUnit);
    }

    @Override
    public void set(String key, Object value, long seconds) {
        cache.set(cacheKey(key), value, seconds);
    }

    @Override
    public Object get(String key) {
        return cache.get(cacheKey(key));
    }


    private String cacheKey(String key){
        StringBuilder keys = new StringBuilder("com:fengxudong:framework");
        if(FrameworkContextHolder.isolation()){
            keys.append(":").append("org:").append(FrameworkContextHolder.getCurrentOrgId().orElse("null"));
        }
        keys.append(":").append(key);
        return keys.toString();
    }
}
