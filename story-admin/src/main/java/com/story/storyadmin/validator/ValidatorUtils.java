package com.story.storyadmin.validator;

import com.story.storyadmin.common.exception.CustomException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * 
 * @ClassName: ValidatorUtils   
 * @Description: hibernate-validator校验工具类
 * @author: liutao
 * @date: 2018年3月16日 上午11:56:34
 */
public class ValidatorUtils {

	private static Validator validator;

	static {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}
	/**
	 * 校验对象
	 * @param object 待校验对象
	 * @param groups 待校验的组
	 * @throws CustomException 校验不通过，则报BusinessException异常
	 */
	public static void validateEntity(Object object, Class<?>... groups) throws CustomException {
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
		if (!constraintViolations.isEmpty()) {
			ConstraintViolation<Object> constraint =
				(ConstraintViolation<Object>)constraintViolations.iterator().next();
			throw new CustomException(constraint.getMessage());
		}
	}
}
