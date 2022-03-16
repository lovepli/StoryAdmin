package com.story.storyadmin.scheduler.base;

import java.lang.annotation.*;

/**
 * 定时任务注解，标有此注解的类，表示是定时任务类，提供默认的任务编号和任务名称
 * @author
 * @ScheduleAnnotation 注解用来在我们的任务上标识这是一个Job，后面我们用于扫描所有的任务，在页面上生成下拉选项
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
@Documented
public @interface ScheduleAnnotation {

	/**
	 * 任务编号，全局必须唯一
	 * @return
	 */
	String jobId() default "";

	/**
	 * 任务名称
	 * 
	 * @return
	 */
	String jobName() default "";

	/**
	 * 任务是否废弃
	 * 
	 * @return
	 */
	boolean discard() default false;
}
