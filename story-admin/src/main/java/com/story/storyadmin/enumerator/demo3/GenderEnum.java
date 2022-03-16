package com.story.storyadmin.enumerator.demo3;

/**
 * @author: 59688
 * @date: 2021/7/21
 * @description: 我们来写一个实现来标识性别 方法一 https://mp.weixin.qq.com/s/Q5E059XI5cBfjKhiRnt0ug
 */
public enum GenderEnum implements Enumerator { // 实现枚举范式接口

    UNKNOWN(0, "未知"),

    MALE(1, "男"),

    FEMALE(2, "女");

    private final Integer code;
    private final String description;

    GenderEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }


    @Override
    public Integer code() {
        return code;
    }

    @Override
    public String description() {
        return description;
    }
}
