package com.story.storyadmin.common;

/**
 * 系统内部的错误信息  TODO 新增公告功能，添加的
 *
 * @author zhyyy
 **/
public class SrotyAdminException extends RuntimeException {
    public SrotyAdminException(String msg) {
        super(msg);
    }
}