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
public class DCourse2 {

    protected String id;

    protected String name;

    protected String teacherId;


}
