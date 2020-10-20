package com.story.storyadmin.service.sysmgr.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.story.storyadmin.constant.enumtype.YNFlagStatusEnum;
import com.story.storyadmin.constant.sysmgr.ScheduleJobConsts;
import com.story.storyadmin.domain.entity.sysmgr.ScheduleJob;
import com.story.storyadmin.mapper.sysmgr.ScheduleJobMapper;
import com.story.storyadmin.scheduler.base.ScheduleAnnotation;
import com.story.storyadmin.service.sysmgr.ScheduleJobService;
import com.story.storyadmin.utils.AnnotationUtils;
import com.story.storyadmin.utils.DateUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 定时任务 服务实现类
 * </p>
 *
 * @author sunnj
 * @since 2019-08-18
 */
@Service
public class ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobMapper, ScheduleJob> implements ScheduleJobService {

    //private static final Logger logger = LoggerFactory.getLogger(ScheduleJobServiceImpl.class);

    @Override
    public boolean checkExistJob(Long id, String jobId){
        QueryWrapper<ScheduleJob> wrapper = new QueryWrapper<>();
        wrapper.eq("yn_flag", YNFlagStatusEnum.VALID.getCode());
        wrapper.eq("job_id",jobId);
        if(id!=null) {
            wrapper.ne("id", id);
        }
        List<ScheduleJob> result= baseMapper.selectList(wrapper);
        return !CollectionUtils.isEmpty(result);
    }

    /**
     * 获取系统的定时任务
     * @return
     */
    @Override
    public List<ScheduleJob> findScheduleJobCombo() {
        // 新键list集合，存储查询出来的定时任务
        List<ScheduleJob> vos = Lists.newArrayList();
        // 从包package中获取所有的Class, 此包为定时任务Job所在包
        List<Class<?>> clsList = AnnotationUtils.getClasses(ScheduleJobConsts.JOB_PKG);
        if(CollectionUtils.isNotEmpty(clsList)) {
            // 遍历集合
            clsList.stream().forEach(c -> {
                // TODO 这里通过反射获取定时任务基础信息，厉害！！！
                Annotation[] annotations = c.getAnnotations();
                if (annotations != null && annotations.length > 0) {
                    // 是否是定时任务注解 annotations[0]表示类上标示的第一个注解
                    if (annotations[0] instanceof ScheduleAnnotation) {
                        ScheduleAnnotation annotation = (ScheduleAnnotation) annotations[0];
                        ScheduleJob vo = new ScheduleJob();
                        // 获取注解上的jobId值,jobName值和定时任务类名
                        vo.setJobId(annotation.jobId());
                        vo.setJobName(annotation.jobName());
                        vo.setJobClass(c.getName());
                        // 遍历添加到list集合
                        vos.add(vo);
                    }
                }
            });
        }
        return vos;
    }

    @Override
    public void updateRuntimeJob(String jobId, Date fireTime, Date previousFireTime, Date nextFireTime, String failReason){
        QueryWrapper<ScheduleJob> wrapper = new QueryWrapper<>();
        wrapper.eq("yn_flag", YNFlagStatusEnum.VALID.getCode());
        wrapper.eq("job_id",jobId);
        List<ScheduleJob> result= baseMapper.selectList(wrapper);
        if(CollectionUtils.isEmpty(result)){
            return ;
        }
        ScheduleJob job= result.get(0);
        job.setFireTime(fireTime);
        if(previousFireTime != null) {
            job.setLastFireTime(previousFireTime);
        }
        job.setNextFireTime(nextFireTime);
        job.setFailReason(failReason);
        job.setModifiedTime(DateUtils.currentDate());
        baseMapper.updateById(job);
    }
}
