package com.story.storyadmin.domain.entity.wind;

import com.baomidou.mybatisplus.annotation.*;
import com.story.storyadmin.domain.entity.sysmgr.User;
import com.story.storyadmin.domain.entity.wind.common.WindBaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * All rights Reserved, Designed By www.sunseagear.com
 *
 * @version V1.0
 * @package com.sunseagear.bbs.modules.sys.entity
 * @title: 操作日志实体
 * @description: 操作日志实体 * @date: 2018-09-30 15:53:25
 * @copyright: 2018 www.sunseagear.com Inc. All rights reserved.
 */

@Data
@TableName("test_table")
public class Table extends WindBaseEntity<Table> {

    private static final long serialVersionUID = 1L;

    @TableField(value = "title")
    private String title; //标题

    @TableField(value = "content")
    private String content; //内容

    @TableField(value = "author")
    private String author; //作者

    @TableField(value = "type")
    private String type; //类型

    @TableField(value = "status")
    private String status; //状态

    @TableField(value = "level")
    private Integer level; //重要程度

    @TableField(value = "tag")
    private String tag; //标签

    @TableField(value = "readings")
    private Integer readings; //阅读数

    @TableField(value = "publish_date")
    private Date publishDate; //发布时间

    /**
     * TableField(exist = false) 注解加载bean属性上，表示当前属性不是数据库的字段，但在项目中必须使用，这样在新增等使用bean的时候，mybatis-plus就会忽略这个，不会报错
     */
    @TableField(exist = false)
    private User user; //发布人

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
