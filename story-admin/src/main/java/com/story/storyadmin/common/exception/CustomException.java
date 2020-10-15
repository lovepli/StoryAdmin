package com.story.storyadmin.common.exception;

/**
 * 自定义异常，为了区分系统异常和更方便系统的特定一些处理
 *
 * @author
 */
public class CustomException extends ApiException {
    public CustomException(String message) {
        super(message);
    }
}

