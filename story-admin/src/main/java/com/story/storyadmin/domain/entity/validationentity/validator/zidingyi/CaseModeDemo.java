package com.story.storyadmin.domain.entity.validationentity.validator.zidingyi;
import com.story.storyadmin.domain.entity.validationentity.validator.zidingyi.annotation.CheckCase;
import lombok.Data;

/**
 * @author: 59688
 * @date: 2021/7/16
 * @description: 自定义校验器的校验对象
 */
@Data
public class CaseModeDemo {

    @CheckCase(value = CaseMode.LOWER,message = "userName必须是小写")
    private String userName;

}
