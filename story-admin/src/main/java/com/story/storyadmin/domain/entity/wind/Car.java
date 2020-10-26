package com.story.storyadmin.domain.entity.wind;

import com.baomidou.mybatisplus.annotation.*;
import com.story.storyadmin.domain.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName("test_car")
@Data
public class Car extends BaseEntity<Car> {
    private static final long serialVersionUID = 1L;

    /**
     * 汽车品牌
     */
    @TableField(value = "name")
    private String name;

    /**
     * 品牌编码
     */
    @TableField(value = "code")
    private String code;

    /** ##################公共部分#########################*/
    @TableField(value = "remarks")
    private String remarks; // 备注

    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private String createBy; // 创建者

    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private Date createDate; // 创建日期

    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    private String updateBy; // 更新者

    @TableField(value = "update_date", fill = FieldFill.UPDATE)
    private Date updateDate; // 更新日期

    @TableField(value = "del_flag", fill = FieldFill.INSERT)
    private String delFlag = "0"; // 删除标记（0：正常；1：删除 ）

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
