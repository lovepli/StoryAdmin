package com.story.storyadmin.domain.entity.validationentity.validateDemo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

/**
 * @author: 59688
 * @date: 2021/7/15
 * @description:
 */
@Data
public class ValidateDemo3 {
    @Email(message = "必须是合法的邮箱地址")
    private String email;

    @Length(min = 5, max = 17, message = "length长度在[5,17]之间")
    private String extField;
}
