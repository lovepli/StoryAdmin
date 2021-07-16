package com.story.storyadmin.domain.entity.validationentity.validator.zidingyi;
import com.story.storyadmin.domain.entity.validationentity.validator.zidingyi.annotation.CheckCase;

/**
 * @author: 59688
 * @date: 2021/7/16
 * @description: 校验对象
 */
public class CaseModeDemo {

    @CheckCase(value = CaseMode.LOWER,message = "userName必须是小写")
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
