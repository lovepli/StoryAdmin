package com.story.storyadmin.constant.enumtype;


import lombok.Getter;

/**
 * @author huangxunhui
 * Date: Created in 18/8/29 上午9:54
 * Description: 返回状态枚举类
 */
@Getter
public enum ResultEnum {

    //验证成功
    TOKEN_CHECK_SUCCESS(20000,"接口验证成功"),

    /**********************校验相关***********************/
    PASSWORD_CHECK_INVALID(30001,"密码错误"),
    CAPTCHA_CHECK_INVALID(30002,"验证码不正确"),
    PARAMETERS_MISSING(30003,"参数不完整"),

    FORMAT_ERROR(30004, "参数格式错误"),
    ARGUMENT_TYPE_MISMATCH(30005, "参数类型不匹配"),


    /**********************用户相关***********************/
    USER_CHECK_INVALID(40001,"用户不存在"),
    ACCOUNT_CHECK_INVALID(40002,"账号不存在"),
    ACCOUNT_LOCK_INVALID(40003,"账号已被锁定"),
    ACCOUNT_CHECK_USERED(40004,"账号已经存在"),
    ACCOUNT_CANNOT_UPDATE(40005,"账号不能修改"),

    /**********************token相关***********************/
    TOKEN_CHECK_ILLEFALITY_ERROR(60001,"非法token"),
    TOKEN_CHECK_OTHER_LOGIN(60002,"其他客户端登录"),
    TOKEN_CHECK_STALE_DATED(60003,"token过期"),

    /**********************其他***********************/
    SERVER_ERROR(50000,"服务端错误"),

    UNKNOWN_EXCEPTION(10000, "未知异常"),
    API_REQUEST_EXCEPTION(10001, "API接口请求失败!"),

    TIME_OUT(70001, "超时"),

    REQ_METHOD_NOT_SUPPORT(70002,"请求方式不支持"),

    FAILE_Id_EXCEPTION(70003,"非法ID"),

    STATUS_ERROR_EXCEPTION(70004,"状态错误"),

    /**********************shiro/接口权限相关***********************/
    API_DO_NOT_UNAUTHORIZED(80001,"接口没有权限，权限不足"),
    SHIRO_UNAUTHORIZED_EXCEPTION(80002,"shiro权限异常"),

    /**********************上传下载导出相关***********************/
    EXPORT_EROOR(90001,"导出错误")

    ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 通过状态码获取枚举对象
     * @param code 状态码
     * @return 枚举对象
     */
    public static ResultEnum getByCode(int code){
        for (ResultEnum resultEnum : ResultEnum.values()) {
            if(code == resultEnum.getCode()){
                return resultEnum;
            }
        }
        return null;
    }

}

