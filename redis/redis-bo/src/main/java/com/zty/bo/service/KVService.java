package com.zty.bo.service;

import java.util.concurrent.TimeUnit;

/**
 * @author tianyi
 * @date 2020-05-02 23:33
 */
public interface KVService {

    public void set(String key, String value, long ttl, TimeUnit timeUnit);

    public String get(String key);
}
