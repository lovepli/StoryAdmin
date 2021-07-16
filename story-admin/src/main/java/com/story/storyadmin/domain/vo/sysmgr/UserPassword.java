package com.story.storyadmin.domain.vo.sysmgr;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 用户密码和新密码表单对象
 */
@Data
public class UserPassword implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "原密码不能为空")
    private String password;

    @NotBlank(message = "新的密码不能为空")
    private String newPassword;

}
