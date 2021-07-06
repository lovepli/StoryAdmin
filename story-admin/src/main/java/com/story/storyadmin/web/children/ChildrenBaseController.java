package com.story.storyadmin.web.children;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * @author: 59688
 * @date: 2021/7/6
 * @description: 通用父类
 */
public abstract class ChildrenBaseController {

    protected Logger longger;

    public ChildrenBaseController(){
        longger = LoggerFactory.getLogger(getClass().getName());
    }
}
