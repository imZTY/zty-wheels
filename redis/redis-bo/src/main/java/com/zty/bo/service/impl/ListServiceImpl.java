package com.zty.bo.service.impl;

import com.zty.bo.service.ListService;
import com.zty.common.dto.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author tianyi
 * @date 2020-05-21 17:57
 */
@Service
public class ListServiceImpl implements ListService {

    private static final Logger log = LoggerFactory.getLogger(ListServiceImpl.class);

    @Resource
    private RedisTemplate<String, MessageDTO> redisTemplate;

    /**
     * 左进右出
     *
     * @param listName 队列名称
     * @param task     TaskDTO
     */
    @Override
    public int push(String listName, MessageDTO task) {
        log.info("[push]向"+listName+"添加任务：{}",task);
        try {
            redisTemplate.opsForList().leftPush(listName, task);
            return 1;
        }catch (Exception e){
            log.error("",e);
            return 0;
        }
    }

    /**
     * 左进右出
     *
     * @param listName 队列名称
     * @return
     */
    @Override
    public MessageDTO pop(String listName) {
        MessageDTO rt = redisTemplate.opsForList().rightPop(listName);
        if (rt != null){
            log.info("[pop]向"+listName+"获取任务：{}",rt);
        }
        return rt;
    }
}
