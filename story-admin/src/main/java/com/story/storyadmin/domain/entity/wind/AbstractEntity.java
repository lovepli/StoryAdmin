package com.story.storyadmin.domain.entity.wind;


import java.io.Serializable;

/**
 * 抽象实体基类
 */
public abstract class AbstractEntity<Long> implements Serializable {


    public abstract java.lang.Long getId();

    public abstract void setId(java.lang.Long id);
}
