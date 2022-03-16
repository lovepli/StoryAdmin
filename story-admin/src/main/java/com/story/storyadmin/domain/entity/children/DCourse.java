package com.story.storyadmin.domain.entity.children;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author: lipan
 * @date: 2021/7/5
 * @description: 课程表
 */
@Data
@ToString
@TableName("d_course")
public class DCourse extends Model<DCourse> {

    private static final  long serialVersionUID =1L;

    @TableId(value = "c_ID",type = IdType.INPUT)
    private String id;

    @TableField("c_name")
    private String name;

    /**
     * 教师编号
     */
    @TableField("teacher_id")
    private String teacherId;

    public DCourse(){}

    public DCourse(String id, String name, String teacherId) {
        this.id = id;
        this.name = name;
        this.teacherId = teacherId;
    }

    @Override
    protected Serializable pkVal(){
        return this.id;
    }
}
