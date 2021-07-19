package com.story.storyadmin.domain.entity.sysmgr;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.story.storyadmin.domain.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: lipan
 * @date: 2020/7/31
 * @description: 部门类
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("st_dept")
public class Dept extends BaseEntity<Dept> {

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
     * 编号路径
     */
    @TableField("full_id")
    private String fullId;

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
