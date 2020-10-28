package com.story.storyadmin.domain.entity.wind;

import com.baomidou.mybatisplus.annotation.*;
import com.story.storyadmin.domain.entity.wind.common.WindBaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
@TableName("sys_dict")
public class WDict extends WindBaseEntity<WDict> {

    private static final long serialVersionUID = 1L;

    /**
     * 分组ID
     */
    @TableField(value = "gid")
    private Long gid;
    /**
     * 键值名称
     */
    @TableField(value = "label")
    private String label;
    /**
     * 值
     */
    @TableField(value = "value")
    private String value;

    /**
     * 排序
     */
    @TableField(value = "sort")
    private Integer sort;
    /**
     * 分组code
     */
    @TableField(exist = false)
    private String code;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
