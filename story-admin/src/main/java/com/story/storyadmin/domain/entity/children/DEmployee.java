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

    @Override
    protected Serializable pkVal(){
        return this.id;
    }
}
