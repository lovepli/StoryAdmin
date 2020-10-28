package com.story.storyadmin.domain.entity.wind.common;

import java.io.Serializable;

/**
 * 抽象实体基类
 */
public abstract class AbstractEntity<ID> implements Serializable {


    public abstract ID getId();

    public abstract void setId(ID id);
}
