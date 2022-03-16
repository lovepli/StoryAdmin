package com.story.storyadmin.enumerator.globalEnum;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.Getter;

/**
 * @author: lipan
 * @date: 2021年09月08日 9:58 下午
 * @description:
 */
@Getter
public enum GenderEnum implements IEnum<Integer> {
    MALE(1, "男"),
    FEMALE(2, "女");

    private final int code;
    private final String descp;

    GenderEnum(final int code, final String descp) {
        this.code = code;
        this.descp = descp;
    }

    @Override
    public Integer getValue() {
        return code;
    }
}
