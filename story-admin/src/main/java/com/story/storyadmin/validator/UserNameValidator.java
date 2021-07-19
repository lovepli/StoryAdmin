package com.story.storyadmin.validator;


import com.story.storyadmin.common.annotation.validationen.UserName;
import org.springframework.util.StringUtils;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义校验器 -- 用户名校验
 */
public class UserNameValidator implements ConstraintValidator<UserName, String> {

    private String regexp;

    @Override
    public void initialize(UserName constraintAnnotation) {
        //获取校验的用户名的格式
        this.regexp = constraintAnnotation.regexp();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!StringUtils.hasText(value)) {
            return true;
        }
        return value.matches(regexp);
    }


}
