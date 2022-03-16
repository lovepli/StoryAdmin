package com.story.storyadmin.domain.entity.sysmgr.expand;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.story.storyadmin.domain.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author: 59688
 * @date: 2020/11/6
 * @description:
 * 使用@Data时默认@equalsandhashcode的状态为callsuper=false
 * @equalsandhashcode(callsuper=false) 表达为在对象比较时不会考虑父类中的成员,仅仅比较子类中的属性就判断是否相同
 * @equalsandhashcode(callsuper=true) 在比较时会考虑父类中的成员,通过父类和子类中的属性一起判断是否相同
 *
 * @Accessors(chain = true) 的set方法会返回对象，可以进行链式设值
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("st2_dept")
public class Dept2 extends BaseEntity<Dept2> {

    private static final long serialVersionUID = 1L;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 排序
     */
    @TableField("show_order")
    private Integer showOrder;

    /**
     * 父ID
     */
    private Long pid;

    /**
     * 祖级列表
     */
    private String ancestors;

    /**
     * 部门描述
     */
    @TableField("dept_desc")
    private String deptDesc;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
