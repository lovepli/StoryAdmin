package com.story.storyadmin.domain.entity;

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
public class BaseEntity<T extends Model<?>> extends Model<T> {

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    protected Long id;

    /**
     * 有效标志  1表示有效，0表示无效，无效不会进行物理删除，做的是逻辑删除
     */
    @TableField("yn_flag")
    protected String ynFlag;

    /**
     * 创建人 creator
     */
    @TableField("creator")
    protected String creator;

    /**
     * 修改人 editor
     */
    @TableField("editor")
    protected String editor;

    /**
     * 创建时间
     */
    @TableField("created_time")
    protected Date createdTime;

    /**
     * 修改时间
     */
    @TableField("modified_time")
    protected Date modifiedTime;

    public BaseEntity() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public String getYnFlag() {
        return ynFlag;
    }

    public void setYnFlag(String ynFlag) {
        this.ynFlag = ynFlag;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    /**
     * 实体类继承 Model 类，并重写 pkVal 方法。return当前类的主键
     * 序列化的ID
     * @return
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
