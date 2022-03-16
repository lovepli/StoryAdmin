package com.story.storyadmin.domain.entity.children;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author: 59688
 * @date: 2021/9/15
 * @description: 薪资等级表
 */
@Data
@ToString
@NoArgsConstructor
@TableName("d_salary_grade")
public class DSalaryGrade extends Model<DSalaryGrade> {
    private static final  long serialVersionUID =1L;

    @TableId(value = "g_id",type = IdType.INPUT)
    private String id;

    /**薪水等级 **/
    @TableId(value = "grade")
    private Integer grade;

    /**最低薪水 **/
    @TableField("low_salary")
    private Integer lowSalary;

    /**最高薪水 **/
    @TableField("high_salary")
    private  Integer highSalary;

    @Override
    protected Serializable pkVal(){
        return this.id;
    }
}


