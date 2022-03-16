package com.story.storyadmin.basic.exception;

/**
 * @author: lipan
 * @date: 2019-06-08
 * @description:  自定义异常
 */
public class BaseException extends RuntimeException {



    //多个构造函数，方便接受传递的异常信息
    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
