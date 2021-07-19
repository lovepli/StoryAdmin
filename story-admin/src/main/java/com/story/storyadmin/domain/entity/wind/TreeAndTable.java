package com.story.storyadmin.domain.entity.wind;
import com.baomidou.mybatisplus.annotation.*;
import com.story.storyadmin.domain.entity.wind.common.WindIdStringEntity;
import lombok.Data;
import java.io.Serializable;


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
public class TreeAndTable  extends WindIdStringEntity<TreeAndTable> {

    private static final long serialVersionUID = 1L;

    @TableField(value = "name")
    private String name;  //部门名称
    @TableField(value = "type")
    private String type;  //类型
    @TableField(value = "tag")
    private String tag;  //标签
    @TableField(value = "area_id")
    private String areaId;  //区域

    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}
