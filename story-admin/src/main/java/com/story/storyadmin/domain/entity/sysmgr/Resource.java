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
 * <p>
 * 菜单表
 * </p>
 *
 * @author sunnj
 * @since 2018-12-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("st_resource")
public class Resource extends BaseEntity<Resource> {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单编号路径
     */
    @TableField("full_id")
    private String fullId;

    /**
     * 图标样式类
     */
    @TableField("icon_class")
    private String iconClass;

    /**
     * 排序
     */
    @TableField("show_order")
    private Integer showOrder;

    /**
     * 链接
     */
    private String url;

    /**
     * 页面路径
     */
    private String component ;

    /**
     * 权限ID
     */
    @TableField("authority_id")
    private Long authorityId;

    /**
     * 父ID
     */
    private Long pid;

    /**
     * 菜单描述
     */
    @TableField("resource_desc")
    private String resourceDesc;

    /**
     * 有效标志
     * 逻辑删除标志
     */
    @TableField("yn_flag")
    private String ynFlag;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 修改人
     */
    private String editor;

    /**
     * 创建时间
     */
    @TableField("created_time")
    private Date createdTime;

    /**
     * 修改时间
     */
    @TableField("modified_time")
    private Date modifiedTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
