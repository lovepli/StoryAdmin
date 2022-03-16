package com.story.storyadmin.web.monitor;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.story.storyadmin.common.exception.StoryServiceException;
import com.story.storyadmin.config.mongo.SysLogAnnotation;
import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.constant.enumtype.YNFlagStatusEnum;
import com.story.storyadmin.domain.entity.sysmgr.ScheduleJob;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.scheduler.service.StorySchedulerService;
import com.story.storyadmin.service.sysmgr.ScheduleJobService;
import com.story.storyadmin.utils.DateMethordUtil.DateUtils;
import com.story.storyadmin.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 定时任务 前端控制器
 * 定时任务增删改查，简单列举了增删改的方法，可以看到在持久化的同时也动态的修改了计划任务
 * </p>
 *
 * @author sunnj
 * @since 2019-08-18
 */
@Api(description = "定时任务")
@RestController
@RequestMapping("/monitor/schedulejob")
public class ScheduleJobController extends BaseController {

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
    @RequiresPermissions("monitor.schedulejob.query")
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
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    /**
     * 根据Id查询
     * @param scheduleJob
     * @return
     */
    @ApiOperation(value = "定时任务" ,  notes="根据Id查询定时任务信息")
    @RequiresPermissions("monitor.schedulejob.query")
    @RequestMapping(value="/find",method = {RequestMethod.POST})
    public Result findById(@RequestBody ScheduleJob scheduleJob){
        ScheduleJob scheduleJobBean= scheduleJobService.getById(scheduleJob.getId());

        Result result = new Result();
        result.setData(scheduleJobBean);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    /**
     * 保存
     * @param scheduleJob
     * @return
     */
    @SysLogAnnotation
    @ApiOperation(value = "定时任务" ,  notes="保存定时任务信息")
    @RequiresPermissions("monitor.schedulejob.save")
    @RequestMapping(value="/save",method = {RequestMethod.POST})
    public Result save(@RequestBody ScheduleJob scheduleJob){
        Result result = null;
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
            result= new Result(true, "修改成功", null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }else{
            scheduleJob.setCreator(UserContext.getCurrentUser().getAccount());
            scheduleJob.setCreatedTime(currentDate);
            scheduleJob.setYnFlag(YNFlagStatusEnum.VALID.getCode());
            // 保存定时任务信息记录到数据库
            scheduleJobService.save(scheduleJob);
            // 新增定时任务
            storySchedulerService.addJob(scheduleJob.getJobId(), scheduleJob.getJobClass(), scheduleJob.getCron(), scheduleJob.getStartTime(), startJob);
            result= new Result(true, "添加成功", null, ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }
        return result;
    }

    /**
     * 删除
     * @param scheduleJob
     * @return
     */
    @SysLogAnnotation
    @ApiOperation(value = "定时任务" ,  notes="删除定时任务信息")
    @RequiresPermissions("monitor.schedulejob.delete")
    @RequestMapping(value="/delete",method = {RequestMethod.POST})
    public Result dropById(@RequestBody ScheduleJob scheduleJob){
        Result result ;
        if(scheduleJob.getId()!=null){
            ScheduleJob delScheduleJob= new ScheduleJob();
            delScheduleJob.setId(scheduleJob.getId());
            delScheduleJob.setYnFlag("0");
            delScheduleJob.setEditor(UserContext.getCurrentUser().getAccount());
            delScheduleJob.setModifiedTime(Date.from(Instant.now()));
            storySchedulerService.removeJob(scheduleJob.getJobId());
            result=new Result(scheduleJobService.updateById(delScheduleJob),"删除成功",null,ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        }else{
            result = new Result(false, "删除失败", null , ResultEnum.PARAMETERS_MISSING.getCode());
        }
        return result;
    }

    /**
     * 获取下拉选项
     *  TODO 这里要先写好后台的定时任务代码，才能有新的定时任务编号，可以供前端添加到数据库
     * @return
     */
    @ApiOperation(value = "定时任务" ,  notes="获取定时任务下拉选项信息")
    @RequestMapping(value="/job-options",method = {RequestMethod.POST,RequestMethod.GET})
    public Result getJobCombo() {
        // 返沪任务编号jobId，任务名称jobName，任务类名jobClass
        List<ScheduleJob> jobList= scheduleJobService.findScheduleJobCombo();
        Result result = new Result();
        result.setData(jobList);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());
        return result;
    }

    /**
     * 检查定时任务是否存在
     * @param schedule
     * @return
     */
    @ApiOperation(value = "定时任务" ,  notes="检查定时任务是否存在")
    @RequiresPermissions("monitor.schedulejob.query")
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
