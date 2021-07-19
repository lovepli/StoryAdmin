package com.story.storyadmin.domain.entity.wind;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.story.storyadmin.domain.entity.wind.common.WindBaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("sys_dict_group")
public class DictGroup extends WindBaseEntity<DictGroup> {

    private static final long serialVersionUID = 1L;

    /**
     * 分组名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 分组编码
     */
    @TableField(value = "code")
    private String code;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
