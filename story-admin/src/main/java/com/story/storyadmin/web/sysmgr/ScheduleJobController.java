package com.story.storyadmin.web.sysmgr;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.constant.Constants;
import com.story.storyadmin.constant.enumtype.YNFlagStatusEnum;
import com.story.storyadmin.domain.StoryServiceException;
import com.story.storyadmin.domain.entity.sysmgr.ScheduleJob;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.scheduler.service.StorySchedulerService;
import com.story.storyadmin.service.sysmgr.ScheduleJobService;
import com.story.storyadmin.utils.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 定时任务 前端控制器
 * </p>
 *
 * @author sunnj
 * @since 2019-08-18
 */
@Api(description = "定时任务")
@RestController
@RequestMapping("/sysmgr/schedulejob")
public class ScheduleJobController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ScheduleJobService scheduleJobService;

    @Autowired
    private StorySchedulerService storySchedulerService;

    /**
     * 分页查询
     * @param scheduleJob
     * @param pageNo
     * @param limit
     * @return
     */
    @ApiOperation(value = "定时任务" ,  notes="查询定时任务列表")
    @RequiresPermissions("sysmgr.schedulejob.query")
    @RequestMapping(value="/list",method = {RequestMethod.POST,RequestMethod.GET})
    public Result list(ScheduleJob scheduleJob,
                       @RequestParam(defaultValue = "1")int pageNo,
                       @RequestParam(defaultValue = "10")int limit){
        Result result = new Result();
        Page<ScheduleJob> page = new Page(pageNo, limit);
        QueryWrapper<ScheduleJob> eWrapper = new QueryWrapper(scheduleJob);
        eWrapper.eq("yn_flag","1");
        IPage<ScheduleJob> list = scheduleJobService.page(page, eWrapper);
        result.setData(list);
        result.setResult(true);
        result.setCode(Constants.TOKEN_CHECK_SUCCESS);
        return result;
    }

    /**
     * 根据Id查询
     * @param scheduleJob
     * @return
     */
    @RequiresPermissions("sysmgr.schedulejob.query")
    @RequestMapping(value="/find",method = {RequestMethod.POST})
    public Result findById(@RequestBody ScheduleJob scheduleJob){
        ScheduleJob scheduleJobBean= scheduleJobService.getById(scheduleJob.getId());

        Result result = new Result();
        result.setData(scheduleJobBean);
        result.setResult(true);
        result.setCode(Constants.TOKEN_CHECK_SUCCESS);
        return result;
    }

    /**
     * 保存
     * @param scheduleJob
     * @return
     */
    @RequiresPermissions("sysmgr.schedulejob.save")
    @RequestMapping(value="/save",method = {RequestMethod.POST})
    public Result save(@RequestBody ScheduleJob scheduleJob){
        // 任务状态判断
        boolean startJob = scheduleJob.getStartJob() != null ? scheduleJob.getStartJob().booleanValue() : false;

        Date currentDate= DateUtils.currentDate();
        scheduleJob.setEditor(UserContext.getCurrentUser().getAccount());
        scheduleJob.setModifiedTime(currentDate);
        // 判断是否是添加或者修改操作
        if(scheduleJob.getId()!=null){
            // 修改定时任务信息记录到数据库
            scheduleJobService.updateById(scheduleJob);
           // 任务是启动状态
            if(startJob) {
                // 修改一个任务的触发时间
                storySchedulerService.updateJobCron(scheduleJob.getJobId(), scheduleJob.getCron(), scheduleJob.getStartTime());
                // 恢复指定jobId的定时任务
                storySchedulerService.resumeJob(scheduleJob.getJobId());
            } else {
                // 暂停指定jobId的定时任务
                storySchedulerService.pauseJob(scheduleJob.getJobId());
            }

        }else{
            scheduleJob.setCreator(UserContext.getCurrentUser().getAccount());
            scheduleJob.setCreatedTime(currentDate);
            scheduleJob.setYnFlag(YNFlagStatusEnum.VALID.getCode());
            // 保存定时任务信息记录到数据库
            scheduleJobService.save(scheduleJob);
            // 新增定时任务
            storySchedulerService.addJob(scheduleJob.getJobId(), scheduleJob.getJobClass(), scheduleJob.getCron(), scheduleJob.getStartTime(), startJob);
        }
        return new Result(true,null,null, Constants.TOKEN_CHECK_SUCCESS);
    }

    /**
     * 删除
     * @param scheduleJob
     * @return
     */
    @RequiresPermissions("sysmgr.schedulejob.delete")
    @RequestMapping(value="/delete",method = {RequestMethod.POST})
    public Result dropById(@RequestBody ScheduleJob scheduleJob){
        Result result ;
        if(scheduleJob.getId()!=null){
            ScheduleJob delScheduleJob= new ScheduleJob();
            delScheduleJob.setId(scheduleJob.getId());
            delScheduleJob.setYnFlag("0");
            delScheduleJob.setEditor(UserContext.getCurrentUser().getAccount());
            delScheduleJob.setModifiedTime(Date.from(Instant.now()));
            result=new Result(scheduleJobService.updateById(delScheduleJob),null,null,Constants.TOKEN_CHECK_SUCCESS);

            storySchedulerService.removeJob(scheduleJob.getJobId());
        }else{
            result = new Result(false, "", null ,Constants.PARAMETERS_MISSING);
        }
        return result;
    }

    /**
     * 获取下拉选项
     *  TODO 这里要先写好后台的定时任务代码，才能有新的定时任务编号，可以供前端添加到数据库
     * @return
     */
    @RequestMapping(value="/job-options",method = {RequestMethod.POST,RequestMethod.GET})
    public Result getJobCombo() {
        // 返沪任务编号jobId，任务名称jobName，任务类名jobClass
        List<ScheduleJob> jobList= scheduleJobService.findScheduleJobCombo();
        return new Result(true, "", jobList ,Constants.TOKEN_CHECK_SUCCESS);
    }

    @RequiresPermissions("sysmgr.schedulejob.query")
    @PostMapping("/check_job_exist")
    public boolean checkJobExist(ScheduleJob schedule) {
        boolean exist = true;
        Long id = schedule.getId();
        String jobId = schedule.getJobId();
        try {
            exist = scheduleJobService.checkExistJob(id, jobId);
        } catch (StoryServiceException e) {
            logger.error("checkJobExist schedulerJob: {} error.", schedule, e);
        }
        return exist;
    }
}
