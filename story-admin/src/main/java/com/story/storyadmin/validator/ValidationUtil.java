package com.story.storyadmin.validator;


import com.story.storyadmin.domain.vo.Result;
import org.apache.commons.collections.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ValidationUtil {
    //手机号校验正则
    public static final String MOBILE_REGX = "^[1][3-9][0-9]{9}$";

    public static final String MOBILE_MSG = "手机号格式错误";

    //用户名校验正则
    public static final String USERNAME_REGX = "^[a-zA-z]\\w{4,19}$";

    public static final String USERNAME_MSG = "账号必须是字母开头，字母、数字、下划线组成，4-20位";


    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * 验证某个对象所有字段
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> Result validateEntity(T obj) {
        Result result = new Result();
        Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
        if (!CollectionUtils.isEmpty(set)) {
            result.setResult(true);
            Map<String, String> errorMsg = new HashMap<>();
            for (ConstraintViolation<T> cv : set) {
                errorMsg.put(cv.getPropertyPath().toString(), cv.getMessage());
            }
            result.setData(errorMsg);
        }
        return result;
    }

    /**
     * 验证某个对象某个字段
     *
     * @param obj
     * @param propertyName
     * @param <T>
     * @return
     */
    public static <T> Result validateProperty(T obj, String propertyName) {
        Result result = new Result();
        Set<ConstraintViolation<T>> set = validator.validateProperty(obj, propertyName, Default.class);
        if (!CollectionUtils.isEmpty(set)) {
            result.setResult(true);
            Map<String, String> errorMsg = new HashMap<>();
            for (ConstraintViolation<T> cv : set) {
                errorMsg.put(propertyName, cv.getMessage());
            }
            result.setData(errorMsg);
        }
        return result;
    }
}
