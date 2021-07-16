package com.story.storyadmin.domain.entity.validationentity2.validator.zidingyi.annotation;


import com.story.storyadmin.domain.entity.validationentity2.validator.zidingyi.MobileValidator;
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
@Constraint(validatedBy = {MobileValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
public @interface Mobile {

    String message() default ValidationUtil.MOBILE_MSG;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String regexp() default ValidationUtil.MOBILE_REGX;

    Pattern.Flag[] flags() default {};

    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        Mobile[] value();
    }
}
