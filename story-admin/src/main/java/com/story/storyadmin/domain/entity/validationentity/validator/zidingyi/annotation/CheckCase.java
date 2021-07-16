package com.story.storyadmin.domain.entity.validationentity.validator.zidingyi.annotation;

import com.story.storyadmin.domain.entity.validationentity.validator.zidingyi.CaseMode;
import com.story.storyadmin.domain.entity.validationentity.validator.zidingyi.CheckCaseValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author: 59688
 * @date: 2021/7/16
 * @description: 自定义注解校验 大小写
 */
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckCaseValidator.class)
@Documented
public @interface CheckCase {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    CaseMode value();
}

