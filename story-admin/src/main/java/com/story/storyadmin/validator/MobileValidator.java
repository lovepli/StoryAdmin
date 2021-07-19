package com.story.storyadmin.validator;

import com.story.storyadmin.common.annotation.validationen.Mobile;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义校验器 -- 手机号码校验
 */
public class MobileValidator implements ConstraintValidator<Mobile, String> {

    private String regexp;

    @Override
    public void initialize(Mobile constraintAnnotation) {
        //获取校验的手机号的格式
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
