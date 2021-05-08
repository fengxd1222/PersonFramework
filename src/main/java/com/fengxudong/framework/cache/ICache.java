package com.fengxudong.framework.cache;

import java.util.concurrent.TimeUnit;

public interface ICache {
    public void set(String key, Object value, long timeout, TimeUnit timeUnit);

    public void set(String key, Object value, long seconds);


    public Object get(String key);
}
