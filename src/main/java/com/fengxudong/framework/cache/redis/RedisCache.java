package com.fengxudong.framework.cache.redis;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

public class RedisCache implements IRedisCache {

    private RedisTemplate<String, Object> redisTemplate;

    public RedisCache(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }


    @Override
    public void set(String key, Object value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key,value,timeout,timeUnit);
    }

    @Override
    public void set(String key, Object value, long seconds) {
        redisTemplate.opsForValue().set(key,value,seconds);
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
