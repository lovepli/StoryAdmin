package com.story.storyadmin.scheduler.listener;

import com.story.storyadmin.domain.StoryServiceException;
import com.story.storyadmin.service.sysmgr.ScheduleJobService;
import com.story.storyadmin.utils.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 自定义任务监听器
 * Quartz  Scheduler 可以对Job(任务)建立一个监听器，分别对任务执行  《之前， 之后， 取消》 3个阶段进行监听。
 * 实现监听器需要实现JobListener接口，然后注册到Scheduler上就可以了
 */
@Service
public class StoryJobListener implements JobListener {
    private static final Logger logger = LoggerFactory.getLogger(StoryJobListener.class);

	public static final String LISTENER_NAME = "StoryJobListener";
	
	@Autowired
	private ScheduleJobService scheduleJobService;

	public StoryJobListener(){}

	@Override
	public String getName() {
		return LISTENER_NAME;
	}

	/**
	 * (1)任务执行前
	 * @param context
	 */
	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
		String jobName = context.getJobDetail().getKey().toString();
		System.out.println("jobToBeExecuted");
		System.out.println("Job : " + jobName + " is going to start...");
	}

	/**
	 * (2)任务被否决
     * 这个方法正常情况下不执行,但是如果当TriggerListener中的vetoJobExecution方法返回true时,那么执行这个方法.
     * 需要注意的是 如果方法(2)执行 那么(1),(3)这个俩个方法不会执行,因为任务被终止了嘛.
	 * @param context
	 */
	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {
		System.out.println("jobExecutionVetoed");
	}

	/**
	 * (3)任务被调度后
     * 任务执行完成后执行,jobException如果它不为空则说明任务在执行过程中出现了异常
	 * @param context
	 * @param jobException
	 */
	@Override
	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
		String jobId = context.getJobDetail().getKey().getName();
		String failReason = null;
		if(jobException != null) {
			failReason = ExceptionUtils.getStackMsg(jobException);
		}
		try {
			scheduleJobService.updateRuntimeJob(jobId, context.getFireTime(), context.getPreviousFireTime(), context.getNextFireTime(), failReason);
		} catch (StoryServiceException e) {
            logger.error("updateRuntimeJob {} error", jobId, e);
		}
	}
}
