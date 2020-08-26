package com.story.storyadmin.common;

/**
 * 向前端传递的异常信息  TODO 新增公告功能，添加的
 *
 * @author zhyyy
 **/
public class SrotyAdminOutException extends RuntimeException {
    private int errorCode;
    private String errorMsg;

    public SrotyAdminOutException(String msg) {
        this(msg, 0, msg);
    }

    public SrotyAdminOutException(String message, int errorCode, String errorMsg) {
        super(message);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public SrotyAdminOutException(String message, Throwable cause, int errorCode, String errorMsg) {
        super(message, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public SrotyAdminOutException(Throwable cause, int errorCode, String errorMsg) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public SrotyAdminOutException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int errorCode, String errorMsg) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public SrotyAdminOutException setErrorCode(int errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public SrotyAdminOutException setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }
}
