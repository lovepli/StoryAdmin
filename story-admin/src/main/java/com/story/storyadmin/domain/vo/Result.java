package com.story.storyadmin.domain.vo;

import com.story.storyadmin.constant.enumtype.ResultEnum;
import lombok.Data;

/**
 * 响应结果对象
 * @author lipan
 * */
@Data
public class Result<T> implements java.io.Serializable {

    private static final long serialVersionUID = 752386055477259305L;

    public static final String SUCCESS = "SUCCESS";
    public static final Integer SUCCESS_CODE = 20000;

    private boolean result = true;
    /**返回码 */
    private Integer code = SUCCESS_CODE;
    /**返回消息 */
    private String message;
    /**返回数据 */
    private T data;

    /**
     * 构造函数
     */
    public Result() {
    }

    /**
     * 构造函数
     * @param result
     * @param data
     */
    public Result(boolean result, T data) {
        this.result = result;
        this.data = data;
    }

    /**
     * 构造函数
     * @param result
     * @param message
     * @param data
     */
    public Result(boolean result, String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    /**
     * 构造函数
     * @param result
     * @param message
     * @param data
     * @param code
     */
    public Result(boolean result, String message, T data, Integer code) {
        this.result = result;
        this.message = message;
        this.data = data;
        this.code = code;
    }

    /**
     * 返回成功的响应结果
     * @param message
     * @param data
     * @param code
     * @param <T>
     * @return
     */
    public static <T> Result<T> getSuccess(String message, T data, Integer code) {
        return new Result<T>(true, message, data, code);
    }

    /**
     * 返回成功的响应结果
     * @param message
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> getSuccess(String message, T data) {
        return getSuccess(message, data, SUCCESS_CODE);
    }

    /**
     * 返回成功的响应结果
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> getSuccess(T data) {
        return getSuccess(SUCCESS, data);
    }


    /******************************************************************************/
    /**
     * 成功
     * @param data 需要返回的数据
     * @return data
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>(true, SUCCESS, data, SUCCESS_CODE);
    }

    /**
     * 成功
     * @return 返回空
     */
    public static <T> Result<T> success() {
        return success(null);
    }

    /**
     * 错误
     * @param resultEnum 错误枚举类
     * @return 错误信息
     */
    public static <T> Result<T> error(ResultEnum resultEnum) {
        return new Result<T>(false, resultEnum.getMsg(), null,resultEnum.getCode());
    }

    /**
     * 错误
     * @param code 状态码
     * @param message 消息
     * @return ResultBean
     */
    public static <T> Result<T> error(Integer code, String message) {
        return new Result<T>(false, message, null,code);
    }

    /**
     * 错误
     * @param message 错误信息
     * @return ResultBean
     */
    public static <T> Result<T> error(String message) {
        return error(-1, message);
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
