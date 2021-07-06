package com.story.storyadmin.domain.entity.children;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: lipan
 * @date: 2021/7/5
 * @description: 教师表
 */
@Data
@TableName("d_teacher")
public class DTeacher extends Model<DTeacher> {

    private static final  long serialVersionUID =1L;

    @TableId(value = "ID",type = IdType.INPUT)
    private String id;

    @TableField("name")
    private String name;

    @Override
    protected Serializable pkVal(){
        return this.id;
    }
}
