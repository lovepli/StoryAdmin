package com.story.storyadmin.web.children;

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
 * @description: 分数表
 */
@Data
@TableName("d_score")
public class DScore extends Model<DScore> {

        private static final  long serialVersionUID =1L;

        @TableId(value = "ID",type = IdType.INPUT)
        private String id;

        @TableField("course")
        private int course;

        @TableField("course_id")
        private String courseId;

        @Override
        protected Serializable pkVal(){
            return this.id;
        }

}
