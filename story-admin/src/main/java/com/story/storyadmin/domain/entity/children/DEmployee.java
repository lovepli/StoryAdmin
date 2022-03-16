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
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: 59688
 * @date: 2021/7/9
 * @description: 雇员表-- 与数据库一一对应
 */
@Data
@ToString
@NoArgsConstructor
@TableName("d_employee")
public class DEmployee extends Model<DEmployee> {
    private static final  long serialVersionUID =1L;

    @TableId(value = "e_id",type = IdType.INPUT)
    private String id;

    @TableField("e_name")
    private String name;

    @TableField("dept_id")
    private  String deptId;

    // 增加属性20210915
    /**岗位名称 **/
    @TableField("job_name")
    private  String jobName;

    /**上级领导员工编号 **/
    @TableField("manager_id")
    private  String managerId;

    /**入职日期 **/
    @TableField("hire_date")
    private Date hireDate;

    /** 薪资 **/
    @TableField("salary")
    private BigDecimal salary;

    /** 奖金，佣金 **/
    @TableField("commission")
    private BigDecimal commission;



    @Override
    protected Serializable pkVal(){
        return this.id;
    }
}
