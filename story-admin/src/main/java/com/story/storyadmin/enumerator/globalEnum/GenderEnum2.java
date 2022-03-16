package com.story.storyadmin.enumerator.globalEnum;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @author: lipan
 * @date: 2021年09月08日 9:59 下午
 * @description: SpringBoot - MyBatis-Plus使用详解15（通用枚举）
 * https://www.hangge.com/blog/cache/detail_2924.html
 */
@Getter
public enum GenderEnum2{
    MALE(1, "男"),
    FEMALE(2, "女");

    @EnumValue
    private final int code;
    private final String descp;

    GenderEnum2(final int code, final String descp) {
        this.code = code;
        this.descp = descp;
    }
}