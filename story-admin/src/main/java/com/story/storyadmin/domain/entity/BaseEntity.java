package com.story.storyadmin.domain.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * Entity基类，提供实现序列化方法
 * @param <T>
 */
public class BaseEntity<T extends Model<?>> extends Model<T> {

    /**
     * 主键id
     */
    protected Long id;

    public BaseEntity() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
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



}
