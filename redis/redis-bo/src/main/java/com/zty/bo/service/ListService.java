package com.zty.bo.service;

import com.zty.common.dto.MessageDTO;

/**
 * 操作普通生产消费队列
 * @author tianyi
 * @date 2020-05-02 21:41
 */
public interface ListService {

    /**
     * 左进右出
     * @param listName 队列名称
     * @param task TaskDTO
     * @return
     */
    public int push(String listName, MessageDTO task);

    /**
     * 左进右出
     * @param listName 队列名称
     * @return
     */
    public MessageDTO pop(String listName);
}
