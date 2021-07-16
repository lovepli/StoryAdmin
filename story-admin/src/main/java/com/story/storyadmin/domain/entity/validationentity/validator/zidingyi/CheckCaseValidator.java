package com.story.storyadmin.domain.entity.validationentity.validator.zidingyi;

import com.story.storyadmin.domain.entity.validationentity.validator.zidingyi.annotation.CheckCase;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author: 59688
 * @date: 2021/7/16
 * @description: 自定义校验器 -- 大小写校验器
 */
public class CheckCaseValidator implements ConstraintValidator<CheckCase, String> {

    private CaseMode caseMode;

    @Override
    public void initialize(CheckCase checkCase) {
        this.caseMode = checkCase.value();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return true;
        }

        if (caseMode == CaseMode.UPPER) {
            return s.equals(s.toUpperCase());
        } else {
            return s.equals(s.toLowerCase());
        }
    }
}