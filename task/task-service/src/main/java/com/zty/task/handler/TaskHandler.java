package com.zty.task.handler;

import java.net.ConnectException;
import java.util.Map;
import java.util.concurrent.Callable;

import com.zty.task.service.TaskRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TaskHandler implements Callable<Void> {

	private Logger logger = LoggerFactory.getLogger(TaskHandler.class);
	
	private TaskRunner taskRunner;
	private String callbackUrl;
	private String logId;
	private Map<String, Object> param;
	private String taskId;

	public TaskHandler(TaskRunner taskRunner, String logId, String callbackUrl, Map<String, Object> param, String taskId) {
		this.taskRunner = taskRunner;
		this.param = param;
		this.logId = logId;
		this.callbackUrl = callbackUrl;
		this.taskId = taskId;
	}

	@Override
	public Void call() throws Exception {
		logger.info("开始执行任务：logId={}, param={}, callbackUrl={}", logId, callbackUrl, param);
		try {
			taskRunner.run(param);
			logger.info("任务完成：logId={}", logId);
		} catch (Exception e) {
			logger.error("任务执行异常：logId={}, e={}", logId, e);
		}
		return null;
	}
	
}
