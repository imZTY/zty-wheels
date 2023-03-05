package com.zty.task.service;

import java.util.Map;

import com.zty.task.dto.TaskResultDto;

/**
 * @author: tianyi.zeng
 * @create: 12/3/2022 - 下午 10:09
 */
public interface TaskRunner {

    TaskResultDto run(Map<String, Object> paramMap);
}
