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
 * 权限表
 * </p>
 *
 * @author sunnj
 * @since 2018-12-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("st_authority")
public class Authority extends BaseEntity<Authority> {

    private static final long serialVersionUID = 1L;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 权限编码
     */
    private String code;
    /**
     * 编号路径
     */
    @TableField("full_id")
    private String fullId;
    /**
     * 权限描述
     */
    @TableField("authority_desc")
    private String authorityDesc;
    /**
     * 排序
     */
    @TableField("show_order")
    private Integer showOrder;
    /**
     * 父ID
     */
    private Long pid;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
