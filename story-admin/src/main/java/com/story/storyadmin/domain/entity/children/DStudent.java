package com.story.storyadmin.domain.entity.children;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: lipan
 * @date: 2021/7/5
 * @description: 学生表
 */
@Data
@TableName("d_student")
public class DStudent extends Model<DStudent> {

    private static final  long serialVersionUID =1L;

    @TableId(value = "s_ID",type = IdType.INPUT)
    private String id;

    @TableField("s_name")
    private String name;

    /**
     *  @DateTimeFormat 用于前端传给后端的时间格式转换，
     *  @JsonFormat 用于后端传给前端的时间格式转换
     */
    @JsonFormat(pattern ="yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    @TableField("s_birthday")
    private Date birthday;

    @TableField("s_sex")
    private char sex;

    @Override
    protected Serializable pkVal(){
        return this.id;
    }

}
