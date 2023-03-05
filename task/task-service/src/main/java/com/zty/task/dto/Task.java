package com.zty.task.dto;

import java.io.Serializable;

public class Task implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static final String JOB_OBJ_KEY = "job_object";
	
	/** 任务ID */
	private String taskId;

	/** 任务名称 */
	private String taskName;

	/** 任务分组 */
	private String groupName;

	/** 时间表达式 */
	private String cronExp;

	/** 执行目标 */
	private String taskTarget;

	/** 执行线程数 */
	private Integer threadsNum;

	/** 附加参数 */
	private String params;

	/** 运行状态*/
	private Integer runStatus;
	
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getCronExp() {
		return cronExp;
	}

	public void setCronExp(String cronExp) {
		this.cronExp = cronExp;
	}

	public String getTaskTarget() {
		return taskTarget;
	}

	public void setTaskTarget(String taskTarget) {
		this.taskTarget = taskTarget;
	}

	public Integer getThreadsNum() {
		return threadsNum;
	}

	public void setThreadsNum(Integer threadsNum) {
		this.threadsNum = threadsNum;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public Integer getRunStatus() {
		return runStatus;
	}

	public void setRunStatus(Integer runStatus) {
		this.runStatus = runStatus;
	}
	
}
