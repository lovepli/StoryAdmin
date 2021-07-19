package com.story.storyadmin.domain.entity.validationentity;


import com.story.storyadmin.validator.annotation.Mobile;
import com.story.storyadmin.validator.annotation.UserName;
import com.story.storyadmin.validator.group3.ValidEmail;
import com.story.storyadmin.validator.group3.ValidMobile;
import com.story.storyadmin.validator.group3.ValidUserName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.List;

/**
 * @author: 59688
 * @date: 2021/7/15
 * @description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ValidateDemo1 {

    @NotBlank(message = "姓名不能为空", groups = {ValidUserName.class})
    @UserName(groups = {ValidUserName.class})
    private String name;

    @NotBlank(message = "手机号不能为空", groups = {ValidMobile.class})
    @Mobile(groups = {ValidMobile.class})
    @Pattern(regexp = "^((13[0-9])|(14[5,6,7,9])|(15[^4])|(16[5,6])|(17[0-9])|(18[0-9])|(19[1,8,9]))\\d{8}$", message = "无效的电话号码")
    private String mobile;

    @NotBlank(message = "电子邮箱不能为空", groups = {ValidEmail.class})
    @Email(message = "电子邮箱格式不正确", groups = {ValidEmail.class})
    private String email;

    @NotBlank(message = "密码不能为空")
    @Length(min = 6,max = 18,message = "密码长度必须在6位到18位之间")
    private String password;

    private String address;

    @NotNull(message = "年龄不能为空")
    @Min(value = 12, message = "允许注册年龄最小为12岁", groups = {ValidEmail.class, ValidMobile.class, ValidUserName.class})
    @Max(value = 24, message = "允许年龄最大为24岁",groups = {ValidEmail.class,ValidMobile.class,ValidUserName.class})
    private Integer age;


    @NotEmpty(message = "联系人不允许为空",groups = {ValidEmail.class,ValidMobile.class,ValidUserName.class})
    @Size(min = 1, max = 3, message = "联系人长度只允许1到3之间",groups = {ValidEmail.class,ValidMobile.class,ValidUserName.class})
    private List<String> contacts;

    @Length(min = 5, max = 17, message = "length长度在[5,17]之间")
    private String length;

    /**@Size不能验证Integer，适用于String, Collection, Map and arrays*/
    /** TODO 比较 @Length  @Size @Range 三者的不同**/
    //@Size(min = 1, max = 3, message = "size在[1,3]之间")
    //private String age2;
    //
    //@Range(min = 150, max = 250, message = "range在[150,250]之间")
    //private int age3;






}
