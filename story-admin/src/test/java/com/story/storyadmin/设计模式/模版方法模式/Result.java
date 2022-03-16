package com.story.storyadmin.设计模式.模版方法模式;

/**
 * @author: lipan
 * @date: 2021/8/22
 * @description:
 */
public class Result {
    //返回码
    private String responseCode;
    //描述
    private String message;
    //数据
    private Object data;

    public Result() {
    }

    public Result(String responseCode, String message) {
        this.responseCode = responseCode;
        this.message = message;
    }

    public Result(String responseCode, String message, Object data) {
        this.responseCode = responseCode;
        this.message = message;
        this.data = data;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
