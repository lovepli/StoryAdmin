package com.story.storyadmin.common.exception;

import com.story.storyadmin.constant.enumtype.ResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义异常,继承RuntimeException非受检查异常
 * @author zhyyy
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class CustomException extends RuntimeException {
    /** 错误码*/
    protected Integer errorCode;
    /** 错误信息*/
    protected Object data;
    /**
     * 方法名称 出现错误的类的第几行
     */
    protected  String method;



    /**
     * 自定义异常
     * @param resultEnum 返回枚举对象
     * @param method     方法
     */
    public CustomException(ResultEnum resultEnum, String method) {
        super(resultEnum.getMsg());
        this.errorCode = resultEnum.getCode();
        this.method = method;
    }

    /**
     * @param code    状态码
     * @param message 错误信息
     * @param method  方法
     */
    public CustomException(Integer code, String message, String method) {
        super(message);
        this.errorCode = code;
        this.method = method;
    }

    /******************************************************************************/
    /**
     * 构造器重载，主要是自己考虑某些异常自定义一些返回码
     * @param errorCode
     * @param message
     * @param data
     * @param e
     */
    public CustomException(Integer errorCode, String message, Object data, Throwable e) {
        super(message, e);
        this.errorCode = errorCode;
        this.data = data;
    }

    /**
     *
     * @param errorCode
     * @param message
     * @param data
     */
    public CustomException(Integer errorCode, String message, Object data) {
        this(errorCode, message, data, null);
    }

    /**
     *
     * @param errorCode
     * @param message
     */
    public CustomException(Integer errorCode, String message) {
        this(errorCode, message, null, null);
    }

    /**
     *
     * @param message
     * @param e
     */
    public CustomException(String message, Throwable e) {
        this(null, message, null, e);
    }

    /**
     *
     */
    public CustomException() {

    }

    /**
     *
     * @param message
     */
    public CustomException(String message) {
          super(message);
    }

    /**
     *
     * @param e
     */
    public CustomException(Throwable e) {
        super(e);
    }

}