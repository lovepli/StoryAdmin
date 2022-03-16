package com.story.storyadmin.domain.entity.wind;

import com.baomidou.mybatisplus.annotation.*;
import com.story.storyadmin.domain.entity.wind.common.WindBaseEntity;
import lombok.Data;
import java.io.Serializable;



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
     * TableField(exist = false) 注解加载bean属性上，表示当前属性不是数据库的字段，但在项目中必须使用，这样在新增等使用bean的时候，mybatis-plus就会忽略这个，不会报错
     */
    @TableField(exist = false)
    private String code;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
