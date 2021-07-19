package com.story.storyadmin.scheduler.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.story.storyadmin.constant.enumtype.YNFlagStatusEnum;
import com.story.storyadmin.domain.entity.sysmgr.ScheduleJob;
import com.story.storyadmin.service.sysmgr.ScheduleJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * SpringBoot - 实现启动时执行指定任务（CommandLineRunner、ApplicationRunner） 参考：https://www.hangge.com/blog/cache/detail_2508.html
 * 有时一些特殊的任务需要在系统启动时执行，例如配置文件加载、数据库初始化等操作。
 * Spring Boot 提供了两种解决方案：CommandLineRunner 和 ApplicationRunner。二者使用方式大体一致，差别主要体现在参数上。
 * ApplicationRunner 接口和 CommandLineRunner 类似，在 Spring 容器启动完成时，继承 ApplicationRunner 接口的类的 run 方法会被自动执行（前提是这个类被Spring 管理）
 * 参考: https://www.letianbiji.com/spring-boot/spring-boot-applicationrunner.html
 *
 * 这里我们在容器启动完成后，立即启动我们的计划任务。
 * 可以看到，所有的Job都是我们从数据库中检索出的任务，自动添加到执行计划中。
 * ApplicationRunner 接口是spring boot提供给我们的一个接口，能够在容器启动完成之后，执行我们自定义的任务，例如这里我们启动所有的Job。
 */
@Component
public class ScheduleJobStarter implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleJobStarter.class);

    @Autowired
    private StorySchedulerService storySchedulerService;

    @Autowired
    private ScheduleJobService scheduleJobService;

    /**
     * 会在服务启动完成后立即执行
     */
    @Override
    public void run(ApplicationArguments args) {
        logger.info("================= 启动所有定时任务 ================");
        List<ScheduleJob> startJobs;
        QueryWrapper<ScheduleJob> query= new QueryWrapper<>();
        query.eq("yn_flag", YNFlagStatusEnum.VALID.getCode());
        query.eq("start_job",true);
        query.orderByAsc("start_time");
        // 数据库查询定时任务
        startJobs = scheduleJobService.list(query);
        // 遍历集合
        startJobs.stream().forEach(j -> {
            // 新增定时任务（默认启动）
            storySchedulerService.addJob(j.getJobId(), j.getJobClass(), j.getCron(), j.getStartTime());
        });
        // 启动所有定时任务
        storySchedulerService.startJobs();
    }
}