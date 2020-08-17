package com.story.storyadmin.scheduler;

import com.story.storyadmin.scheduler.base.ScheduleAnnotation;
import com.story.storyadmin.scheduler.base.BaseJob;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 标有@ScheduleAnnotation注解的类，表示的是执行定时任务的类，提供默认的任务编号和任务名称
 */
@ScheduleAnnotation(jobId = "StoryDemoJob", jobName = "框架演示Job")
public class StoryDemoJob extends BaseJob {

    private static final Logger logger = LoggerFactory.getLogger(StoryDemoJob.class);

    /**
     * 打印日志任务
     * @param context
     */
	@Override
	public void execute(JobExecutionContext context){
        logger.info("STORY-ADMIN： 你好！");
	}

}
