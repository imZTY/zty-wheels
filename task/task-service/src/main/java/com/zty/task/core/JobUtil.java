package com.zty.task.core;

import com.zty.task.handler.JobHandler;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;

import com.zty.task.dto.Task;

/**
 * quartz任务调度简单实现
 * @author xiaojianchen
 *
 */
public class JobUtil {
	
	public static Scheduler scheduler;
	
	public static JobKey getJobKey(String name, String group) {
		return JobKey.jobKey(name, group);
	}
	
	public static TriggerKey getTriggerKey(String name, String group) {
		return TriggerKey.triggerKey(name, group);
	}
	
	public static boolean checkExists(String name, String group) throws SchedulerException {
		return scheduler.checkExists(getJobKey(name, group));
	}
	
	/**
	 * 将任务加入调度中心
	 * @param task
	 * @throws SchedulerException
	 */
	public static void createScheduleJob(Task task) throws SchedulerException {
		JobDetail detail = JobBuilder.newJob(JobHandler.class)
				.withIdentity(getJobKey(task.getTaskId(), task.getGroupName()))
				.build();
		detail.getJobDataMap().put("job_object", task);
		
		CronScheduleBuilder builder = CronScheduleBuilder.cronSchedule(task.getCronExp());
		CronTrigger trigger = (CronTrigger) TriggerBuilder.newTrigger()
				.withIdentity(getTriggerKey(task.getTaskId(), task.getGroupName()))
				.withSchedule(builder).build();
		
		scheduler.scheduleJob(detail, trigger);
	}

	/**
	 * 更新调度中心的任务
	 * @param task
	 * @throws SchedulerException
	 */
	public static void updateScheduleJob(Task task) throws SchedulerException {
		TriggerKey triggerKey = getTriggerKey(task.getTaskId(), task.getGroupName());
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		
		CronScheduleBuilder builder = CronScheduleBuilder.cronSchedule(task.getCronExp());
		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
				.withSchedule(builder).build();
		scheduler.rescheduleJob(triggerKey, trigger);
	}
	
	/**
	 * 从调度中心删除任务
	 * @param task
	 * @throws SchedulerException
	 */
	public static void deleteScheduleJob(Task task) throws SchedulerException {
		scheduler.deleteJob(getJobKey(task.getTaskId(), task.getGroupName()));
	}
	
	/**
	 * 暂停任务
	 * @param task
	 * @throws SchedulerException
	 */
	public static void pauseScheduleJob(Task task) throws SchedulerException {
		scheduler.pauseJob(getJobKey(task.getTaskId(), task.getGroupName()));
	}
	
	/**
	 * 重启任务
	 * @param task
	 * @throws SchedulerException
	 */
	public static void resumeScheduleJob(Task task) throws SchedulerException {
		scheduler.resumeJob(getJobKey(task.getTaskId(), task.getGroupName()));
	}
	
	/**
	 * TODO 立即执行
	 * @param task
	 * @throws SchedulerException
	 */
	public static void runOnce(Task task) throws SchedulerException {
		JobKey jobKey = getJobKey(task.getTaskId(), task.getGroupName());
		JobDetail jobDetail = scheduler.getJobDetail(jobKey);
//		jobDetail.getJobDataMap().put(TransmittableContextKey.TRACE_ID,
//		        TransmittableContext.get(TransmittableContextKey.TRACE_ID)); // 将当前traceId放入参数中传递
		scheduler.triggerJob(jobKey, jobDetail.getJobDataMap());
	}
}
