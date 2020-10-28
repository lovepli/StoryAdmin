package com.story.storyadmin.domain.entity.wind;

import com.baomidou.mybatisplus.annotation.*;
import com.story.storyadmin.domain.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * All rights Reserved, Designed By www.sunseagear.com
 *
 * @version V1.0
 * @package test.treeandtable
 * @title: 左树右表控制器
 * @description: 左树右表控制器
 * @author: admin
 * @date: 2019-11-13 21:24:49
 * @copyright: www.sunseagear.com Inc. All rights reserved.
 */

@Data
@TableName("test_tree_and_table")
public class TreeAndTable  extends BaseEntity<TreeAndTable> {

    private static final long serialVersionUID = 1L;

    // TODO 父级继承过来的 随机字符串
//    @TableId(value = "id", type = IdType.UUID)
//    String id;

    @TableField(value = "name")
    private String name;  //部门名称
    @TableField(value = "type")
    private String type;  //类型
    @TableField(value = "tag")
    private String tag;  //标签
    @TableField(value = "area_id")
    private String areaId;  //区域

    /** ##################公共部分#########################*/
    @TableField(value = "remarks")
    private String remarks; // 备注

    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private String createBy; // 创建者

    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private Date createDate; // 创建日期

    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    private String updateBy; // 更新者

    @TableField(value = "update_date", fill = FieldFill.UPDATE)
    private Date updateDate; // 更新日期

    @TableField(value = "del_flag", fill = FieldFill.INSERT)
    private String delFlag = "0"; // 删除标记（0：正常；1：删除 ）

    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}
