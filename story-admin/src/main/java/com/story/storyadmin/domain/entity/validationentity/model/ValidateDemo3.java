package com.story.storyadmin.domain.entity.validationentity.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

/**
 * @author: 59688
 * @date: 2021/7/15
 * @description: 校验对象ValidateDemo2的子对象，测试校验对象和子对象
 */
@Data
public class ValidateDemo3 {
    @Email(message = "必须是合法的邮箱地址")
    private String email;

    @Length(min = 5, max = 17, message = "length长度在[5,17]之间")
    private String extField;
}
