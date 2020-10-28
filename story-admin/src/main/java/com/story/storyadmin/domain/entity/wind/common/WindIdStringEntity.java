package com.story.storyadmin.domain.entity.wind.common;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity基类，提供实现序列化方法
 * @param <T>
 */
public class WindIdStringEntity<T extends Model<?>> extends Model<T> {

    /**
     * 主键id 为 String
     */
    @TableId(value = "id", type = IdType.UUID)
    protected String id;

    @TableField(value = "remarks")
    protected String remarks; // 备注

    @TableField(value = "create_by", fill = FieldFill.INSERT)
    protected String createBy; // 创建者

    @TableField(value = "create_date", fill = FieldFill.INSERT)
    protected Date createDate; // 创建日期

    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    protected String updateBy; // 更新者

    @TableField(value = "update_date", fill = FieldFill.UPDATE)
    protected Date updateDate; // 更新日期

    @TableField(value = "del_flag", fill = FieldFill.INSERT)
    protected String delFlag = "0"; // 删除标记（0：正常；1：删除 ）

    public WindIdStringEntity() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    /**
     * 序列化的ID
     * @return
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
