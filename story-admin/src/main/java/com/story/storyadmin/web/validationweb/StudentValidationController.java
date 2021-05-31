package com.story.storyadmin.web.validationweb;

import com.story.storyadmin.domain.entity.validationentity.StudentValidation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * @author: 59688
 * @date: 2021/5/31
 * @description: https://www.cnblogs.com/coloz/p/10697427.html
 */
@RequestMapping("/validationDemo")
public class StudentValidationController {
    /**
     * 注意在使用 @NotBlank 等注解时，一定要和 @valid 一起使用，否则 @NotBlank 不起作用。
     * @param dto
     * @param results
     * @return
     */
    @RequestMapping("/create")
    @ResponseBody
    public String create(@RequestBody @Valid StudentValidation dto, BindingResult results) {
        if (results.hasErrors()) {
            return results.getFieldError().getDefaultMessage();
        }
        return dto.toString();
    }
}
