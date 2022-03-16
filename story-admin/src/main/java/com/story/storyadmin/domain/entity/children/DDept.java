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
 * @description: 部门表--与数据库一一对应
 */
@Data
@ToString
@NoArgsConstructor
@TableName("d_dept")
public class DDept   extends Model<DDept>{

    private static final  long serialVersionUID =1L;

    @TableId(value = "d_id",type = IdType.INPUT)
    private String id;

    @TableField("d_name")
    private String name;

    @TableField("parent_id")
    private String parentId;

    // 增加属性20210915
    /**部门地址 **/
    @TableField("address")
    private  String address;

    @Override
    protected Serializable pkVal(){
            return this.id;
        }
}
