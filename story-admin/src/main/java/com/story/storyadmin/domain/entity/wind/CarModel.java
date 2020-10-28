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

    @TableField(value = "car_id")
    private Long carId;
    @TableField(value = "name")
    private String name;
    @TableField(value = "value")
    private String value;

    @TableField(value = "sort")
    private Integer sort;
    @TableField(exist = false)
    private String code;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}
