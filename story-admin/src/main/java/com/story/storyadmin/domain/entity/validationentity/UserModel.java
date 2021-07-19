package com.story.storyadmin.domain.entity.validationentity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserModel {

    //@NotBlank(message = "姓名不能为空", groups = {ValidUserName.class})
    //@UserName(groups = {ValidUserName.class})
    private String name;

    //@NotBlank(message = "手机号不能为空", groups = {ValidMobile.class})
    //@Mobile(groups = {ValidMobile.class})
    private String mobile;

    //@NotBlank(message = "电子邮箱不能为空", groups = {ValidEmail.class})
    //@Email(message = "电子邮箱格式不正确", groups = {ValidEmail.class})
    private String email;

    private String password;

    private String address;

    @NotNull(message = "年龄不能为空")
    //@Min(value = 12, message = "允许注册年龄最小为12岁", groups = {ValidEmail.class,ValidMobile.class,ValidUserName.class})
    //@Max(value = 24, message = "允许年龄最大为24岁",groups = {ValidEmail.class,ValidMobile.class,ValidUserName.class})
    private Integer age;

    //@NotEmpty(message = "联系人不允许为空",groups = {ValidEmail.class,ValidMobile.class,ValidUserName.class})
    //@Size(min = 1, max = 3, message = "联系人长度只允许1到3之间",groups = {ValidEmail.class,ValidMobile.class,ValidUserName.class})
    private List<String> contacts;


//##############################通过使用注解进行数据格式化#############################################

    // 传日期类型
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    //正常数字类型
    @NumberFormat(style = NumberFormat.Style.NUMBER,pattern = "#,###")
    private int total;

    //百分数
    @NumberFormat(style = NumberFormat.Style.PERCENT)
    private double discount;

    // 货币
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private double money;
}
