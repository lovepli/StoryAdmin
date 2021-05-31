package com.story.storyadmin.common.annotation.validationgroup;

import com.story.storyadmin.common.validator.UserNameValidator;
import com.story.storyadmin.utils.validation.ValidationUtil;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {UserNameValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
public @interface UserName {

    String message() default ValidationUtil.USERNAME_MSG;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String regexp() default ValidationUtil.USERNAME_REGX;

    Pattern.Flag[] flags() default {};

    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        UserName[] value();
    }
}