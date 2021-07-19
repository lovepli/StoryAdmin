package com.story.storyadmin.scheduler;

import com.story.storyadmin.scheduler.base.BaseJob;
import com.story.storyadmin.scheduler.base.ScheduleAnnotation;
import com.story.storyadmin.service.sysmgr.BackupService;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 标有@ScheduleAnnotation注解的类，表示的是执行定时任务的类，提供默认的任务编号和任务名称
 * 继承基类抽象类BaseJob
 */
@ScheduleAnnotation(jobId = "DbBackupJob", jobName = "数据库备份Job")
public class DbBackupJob extends BaseJob {

    private static final Logger logger = LoggerFactory.getLogger(DbBackupJob.class);

	@Autowired
	private BackupService backupService;

	/**
	 * 数据备份任务
	 */
	@Override
	public void execute(JobExecutionContext context) {
        logger.info("DbBackupJob备份任务执行开始：");
		backupService.backup();
        logger.info("DbBackupJob备份任务执行完毕！");
	}

}
