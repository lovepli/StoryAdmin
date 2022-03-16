package com.story.storyadmin.scheduler.service;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import com.story.storyadmin.scheduler.listener.StoryJobListener;
import java.util.Date;

/**
 * 定义一个任务
 * 首先我们来明确几个概念：
 * Scheduler:主要负责调度任务。
 * Trigger:负责执行任务的触发器。
 * JobListener:任务执行的监听器，可以监听到任务执行前、后以及未能成功执行抛出异常。
 * Job及JobDetail:被执行的任务，Scheduler真正调度的对象。
 *
 * 知道了Scheduler，那么我们需要要知道如何启动，停止，变更以及移除一个任务，所以定义任务动态变更的服务类，包含对任务用到的所有操作。
 */
@Service
public class StorySchedulerService {

    /**
     * 任务组和触发组
     */
	private static final String JOB_GROUP = "STORY_JOB_GROUP";
	private static final String TRIGGER_GROUP = "STORY_TRIGGER_GROUP";
	
	@Autowired
	@Qualifier("storySchedulerFactory")
	private SchedulerFactoryBean schedulerFactory;

	@Autowired
	private StoryJobListener storyJobListener;

	/**
	 * 新增定时任务（默认启动）
	 * 
	 * @param jobId
	 * @param jobClazz
	 * @param cron
	 * @param startTime
	 */
	public void addJob(String jobId, String jobClazz, String cron, Date startTime) {
		addJob(jobId, jobClazz, cron, startTime, true);
	}
	
	/**
	 * 新增定时任务
	 * 
	 * @param jobId
	 * @param jobClazz
	 * @param cron
	 * @param startTime
	 * @param startJob
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addJob(String jobId, String jobClazz, String cron, Date startTime, boolean startJob) {
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			Class jobClass = Class.forName(jobClazz);
            // 设置Job的名字和组
			JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobId, JOB_GROUP).build();
			TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
			triggerBuilder.withIdentity(jobId, TRIGGER_GROUP);
			if(startTime == null || new Date().compareTo(startTime) > 0) {
				triggerBuilder.startNow();
			} else {
				triggerBuilder.startAt(startTime);
			}
			triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
			CronTrigger trigger = (CronTrigger) triggerBuilder.build();
			scheduler.scheduleJob(jobDetail, trigger);
			scheduler.getListenerManager().addJobListener(storyJobListener);
			if(!startJob) {
				pauseJob(jobId);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 修改一个任务的触发时间
	 * 
	 * @param jobId
	 * @param cron
	 * @param startTime
	 */
	public void updateJobCron(String jobId, String cron, Date startTime) {
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			TriggerKey triggerKey = TriggerKey.triggerKey(jobId, TRIGGER_GROUP);
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			if (trigger == null) {
				return;
			}
			String oldTime = trigger.getCronExpression();
			if (!oldTime.equalsIgnoreCase(cron)) {
				TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
				triggerBuilder.withIdentity(jobId, TRIGGER_GROUP);
				if(startTime == null || new Date().compareTo(startTime) > 0) {
					triggerBuilder.startNow();
				} else {
					triggerBuilder.startAt(startTime);
				}
				triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
				trigger = (CronTrigger) triggerBuilder.build();
				scheduler.rescheduleJob(triggerKey, trigger);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 移除指定jobId的定时任务
	 * @param jobId
	 */
	public void removeJob(String jobId) {
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			TriggerKey triggerKey = TriggerKey.triggerKey(jobId, TRIGGER_GROUP);
			scheduler.pauseTrigger(triggerKey);
			scheduler.unscheduleJob(triggerKey);
			scheduler.deleteJob(JobKey.jobKey(jobId, JOB_GROUP));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 暂停指定jobId的定时任务
	 * 
	 * @param jobId
	 */
	public void pauseJob(String jobId) {
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			JobKey jobKey = JobKey.jobKey(jobId, JOB_GROUP);  
			scheduler.pauseJob(jobKey);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 恢复指定jobId的定时任务
	 * 
	 * @param jobId
	 */
	public void resumeJob(String jobId) {
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			JobKey jobKey = JobKey.jobKey(jobId, JOB_GROUP);  
			scheduler.resumeJob(jobKey);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 启动所有定时任务
	 */
	public void startJobs() {
		try {
			Scheduler sched = schedulerFactory.getScheduler();
			sched.start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 关闭所有定时任务
	 */
	public void shutdownJobs() {
		try {
			Scheduler sched = schedulerFactory.getScheduler();
			if (!sched.isShutdown()) {
				sched.shutdown();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
