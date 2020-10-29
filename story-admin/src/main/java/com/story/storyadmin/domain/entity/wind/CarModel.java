package com.story.storyadmin.domain.entity.wind;

import com.baomidou.mybatisplus.annotation.*;
import com.story.storyadmin.domain.entity.wind.common.WindBaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@TableName("test_car_model")
@Data
public class CarModel extends WindBaseEntity<CarModel> {

    private static final long serialVersionUID = 1L;

    /**
     * 分组ID
     */
    @TableField(value = "car_id")
    private Long carId;
    /**
     * 键值名称
     */
    @TableField(value = "name")
    private String name;
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
