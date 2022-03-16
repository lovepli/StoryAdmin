package com.story.storyadmin.domain.entity.children.myBatisTypeHandlerDO.demo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * @author: lipan
 * @date: 2021年09月09日 6:59 下午
 * @description: https://blog.csdn.net/weixin_39151984/article/details/113437805?utm_medium=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-1.no_search_link&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-1.no_search_link
 */
@Data
@Entity
//@TableName(value = "test")
public class TestEntity implements java.io.Serializable {
    /** 序列号 */
    /** 主键,雪花算法产生 */
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "主键,雪花算法产生")
    @TableId
    private Long id;

    @TableField("name")
    private String name;

    /** 创建人 */
    @ApiModelProperty(value = "创建人")
    @TableField(value="create_by",fill = FieldFill.INSERT)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createBy;

    /** 创建时间 */
    @ApiModelProperty(value = "创建时间")
    @TableField(value="create_date",fill = FieldFill.INSERT)
    @JsonFormat(timezone = "GMT+8",shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;

    /** 修改者 */
    @ApiModelProperty(value = "修改者")
    @TableField(value="modified_by" ,fill = FieldFill.UPDATE)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long modifiedBy;

    /** 修改时间 */
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(timezone = "GMT+8",shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value="modified_date",fill = FieldFill.UPDATE)
    private LocalDateTime modifiedDate;

    /** 是否删除(0,否；1,是) */
    @ApiModelProperty(value = "是否删除(0,否；1,是)")
    @TableField(value = "is_deleted",fill = FieldFill.INSERT)
    private Integer isDeleted;

}

