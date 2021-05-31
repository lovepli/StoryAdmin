package com.story.storyadmin.domain.entity.validationentity.model;


import com.story.storyadmin.common.annotation.validationgroup.Mobile;
import com.story.storyadmin.common.annotation.validationgroup.UserName;
import com.story.storyadmin.common.validator.group.ValidEmail;
import com.story.storyadmin.common.validator.group.ValidMobile;
import com.story.storyadmin.common.validator.group.ValidUserName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserModel {

    @NotBlank(message = "姓名不能为空", groups = {ValidUserName.class})
    @UserName(groups = {ValidUserName.class})
    private String name;

    @NotBlank(message = "手机号不能为空", groups = {ValidMobile.class})
    @Mobile(groups = {ValidMobile.class})
    private String mobile;

    @NotBlank(message = "电子邮箱不能为空", groups = {ValidEmail.class})
    @Email(message = "电子邮箱格式不正确", groups = {ValidEmail.class})
    private String email;

    private String password;

    private String address;

    @NotNull(message = "年龄不能为空")
    @Min(value = 12, message = "允许注册年龄最小为12岁", groups = {ValidEmail.class,ValidMobile.class,ValidUserName.class})
    @Max(value = 24, message = "允许年龄最大为24岁",groups = {ValidEmail.class,ValidMobile.class,ValidUserName.class})
    private Integer age;

    @NotEmpty(message = "联系人不允许为空",groups = {ValidEmail.class,ValidMobile.class,ValidUserName.class})
    @Size(min = 1, max = 3, message = "联系人长度只允许1到3之间",groups = {ValidEmail.class,ValidMobile.class,ValidUserName.class})
    private List<String> contacts;
}
