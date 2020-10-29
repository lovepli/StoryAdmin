package com.story.storyadmin.domain.entity.wind;
import com.baomidou.mybatisplus.annotation.*;
import com.story.storyadmin.domain.entity.wind.common.WindBaseEntity;
import lombok.Data;
import java.io.Serializable;


@TableName("test_car")
@Data
public class Car extends  WindBaseEntity<Car> {
    private static final long serialVersionUID = 1L;

    /**
     * 汽车品牌(分组名称)
     */
    @TableField(value = "name")
    private String name;

    /**
     * 品牌编码(分组编码)
     */
    @TableField(value = "code")
    private String code;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
