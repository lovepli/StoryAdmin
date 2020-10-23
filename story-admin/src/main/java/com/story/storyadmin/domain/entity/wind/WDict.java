package com.story.storyadmin.domain.entity.wind;

import com.baomidou.mybatisplus.annotation.*;
import com.story.storyadmin.domain.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
@TableName("sys_dict")
public class WDict extends BaseEntity<WDict> {

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


    @TableField(value = "remarks")
    protected String remarks; // 备注

    @TableField(value = "create_by", fill = FieldFill.INSERT)
    protected String createBy; // 创建者

    @TableField(value = "create_date", fill = FieldFill.INSERT)
    protected Date createDate; // 创建日期

    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    protected String updateBy; // 更新者

    @TableField(value = "update_date", fill = FieldFill.UPDATE)
    protected Date updateDate; // 更新日期

    @TableField(value = "del_flag", fill = FieldFill.INSERT)
    protected String delFlag = "0"; // 删除标记（0：正常；1：删除 ）

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
