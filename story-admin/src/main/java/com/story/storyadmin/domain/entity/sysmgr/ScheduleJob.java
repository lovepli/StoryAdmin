package com.story.storyadmin.domain.entity.sysmgr;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.story.storyadmin.domain.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 定时任务
 * </p>
 *
 * @author sunnj
 * @since 2019-08-18
 * 动态增删变更任务
 * 既然要动态的变更任务，那我们就将任务保存在数据库中，没此项目启动，就不用再次配置。定义我们的PO类，上面的用到的ScheduleJob便是出自这里：
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("st_schedule_job")
public class ScheduleJob extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * JobID
     */
    private String jobId;

    /**
     * Job名称
     */
    private String jobName;

    /**
     * cron表达式
     */
    private String cron;

    /**
     * 启动状态
     */
    private Boolean startJob;

    /**
     * 定时任务类名
     */
    private String jobClass;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 触发时间
     */
    private Date fireTime;

    /**
     * 上次触发时间
     */
    private Date lastFireTime;

    /**
     * 下次触发时间
     */
    private Date nextFireTime;

    /**
     * Job描述
     */
    private String jobDesc;

    /**
     * 失败原因
     */
    private String failReason;

    /**
     * 有效标志
     */
    private String ynFlag;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 修改人
     */
    private String editor;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 修改时间
     */
    private Date modifiedTime;

}
