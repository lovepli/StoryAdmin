package com.story.storyadmin.common.exception;

/**
 * 向前端传递的异常信息
 * api超类异常，继承RuntimeException非受检查异常，其他类继承ApiException类
 * @author zhyyy
 **/
public class ApiException extends RuntimeException {
    /** 错误码*/
    protected Integer errorCode;
    /** 错误信息*/
    protected Object data;

    /**
     * 构造器重载，主要是自己考虑某些异常自定义一些返回码
     * @param errorCode
     * @param message
     * @param data
     * @param e
     */
    public ApiException(Integer errorCode, String message, Object data, Throwable e) {
        super(message, e);
        this.errorCode = errorCode;
        this.data = data;
    }

    public ApiException(Integer errorCode, String message, Object data) {
        this(errorCode, message, data, null);
    }

    public ApiException(Integer errorCode, String message) {
        this(errorCode, message, null, null);
    }

    public ApiException(String message, Throwable e) {
        this(null, message, null, e);
    }

    public ApiException() {

    }
    public ApiException(String message) {
          super(message);
    }

    public ApiException(Throwable e) {
        super(e);
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}