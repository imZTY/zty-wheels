package com.zty.bo.service.impl;

import com.zty.bo.service.KVService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author tianyi
 * @date 2020-05-02 23:39
 */
@Service
public class KVServiceImpl implements KVService {

    private static final Logger log = LoggerFactory.getLogger(KVServiceImpl.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void set(String key, String value, long ttl, TimeUnit timeUnit) {
        stringRedisTemplate.opsForValue().set(key, value, ttl, timeUnit);
    }

    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }
}
