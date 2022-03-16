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
import java.util.List;

/**
 * @author: 59688
 * @date: 2021/7/9
 * @description: 部门表--与数据库一一对应
 */
@Data
@ToString
@NoArgsConstructor
public class DDeptPOJO extends Model<DDeptPOJO>{

    private static final  long serialVersionUID =1L;

    private String id;

    private String name;

    private String parentId;

    /**
     * 子节点
     * TableField(exist = false) 注解加载bean属性上，表示当前属性不是数据库的字段，但在项目中必须使用，这样在新增等使用bean的时候，mybatis-plus就会忽略这个，不会报错
     */
    @TableField(exist = false)
    private List<DDeptPOJO> childNode;

    @Override
    protected Serializable pkVal(){
            return this.id;
        }
}
