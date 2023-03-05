package com.zty.bo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.zty.bo.service.HashService;
import com.zty.common.constant.RedisConstant;

/**
 * @author tianyi
 * @date 2021-04-06 00:26
 */
@Service
public class HashServiceImpl implements HashService {

    private static final Logger log = LoggerFactory.getLogger(HashServiceImpl.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void put(String hash, String hashKey, String hashValue) {
        stringRedisTemplate.opsForHash().put(hash, hashKey, hashValue);
    }

    @Override
    public String get(String hash, String hashKey) {
        return String.valueOf(stringRedisTemplate.opsForHash().get(hash, hashKey));
    }
}
