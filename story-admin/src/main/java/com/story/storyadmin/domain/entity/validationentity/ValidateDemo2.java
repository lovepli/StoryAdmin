package com.story.storyadmin.domain.entity.validationentity;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author: 59688
 * @date: 2021/7/15
 * @description:
 */
@Data
public class ValidateDemo2 {
    @Size(min = 3,max = 5,message = "list的Size在[3,5]")
    private List<String> list;

    @NotNull
    @Valid
    private ValidateDemo3 demo3;
}