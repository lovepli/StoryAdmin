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
