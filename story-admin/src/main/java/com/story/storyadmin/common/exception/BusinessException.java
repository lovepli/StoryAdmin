package com.story.storyadmin.common.exception;

/**
 * 自定义一个特定的异常，继承CustomException
 * 暂时不使用
 *
 * @author
 */
public class BusinessException extends CustomException {
    public BusinessException(String message) {
        super(message);
    }
}

