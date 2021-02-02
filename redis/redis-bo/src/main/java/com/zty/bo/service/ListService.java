package com.zty.bo.service;

import com.zty.common.dto.TaskDTO;

import java.util.concurrent.TimeUnit;

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
    public int push(String listName, TaskDTO task);

    /**
     * 左进右出
     * @param listName 队列名称
     * @return
     */
    public TaskDTO pop(String listName);
}
