package com.story.storyadmin.domain.entity.sysmgr;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.story.storyadmin.domain.entity.BaseEntity;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author sunnj
 * @since 2018-12-28
 */
@Data
//@Builder
@EqualsAndHashCode(callSuper = true) //@EqualsAndHashCode 和@Data注解的使用说明 https://blog.csdn.net/zhanlanmg/article/details/50392266
@Accessors(chain = true) //chain的中文含义是链式的，设置为true，则setter方法返回当前对象
@TableName("st_user")
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity<User> {

  /**
   * 如果一个实现了Serializable的类没有serialVersionUID属性，IDE(比如Eclipse)通常会报这样一个warning: the serializable class
   * Foo does not declare a static final serialVersionUID field of type long
   *
   * <p>那这个serialVersionUID是做什么用的呢？ serialVersionUID
   * 表示可序列化类的版本，在反序列化对象时，用来确认序列化与反序列化该对象所使用的类的版本是否兼容。
   * 如果类的版本不一致，那么反序列化将不能正常进行，抛出InvalidClassException。
   *
   * <p>如果一个可序列化的类没有包含serialVersionUID，运行时会根据这个类的特征自动计算出一个serialVersionUID。 那么，为什么不能用默认的这个实现呢，似乎更省事?
   * 因为不同的编译器实现会导致同一个类的源代码文件，被计算出不同的serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

    /**
     * 账号 JSR 303 权限校验框架
     */
    @NotBlank(message = "账号不能为空")
    @TableField("account")
    private String account;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空")
    //@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[A-Za-z0-9]{6,20}$", message = "用户名必须要有一个小写字母，一个大写字母和一个数字，并且是6-20位")
    private String name;

    /**
     * 年龄
     */
    //@Range(min = 15,max = 60,message = "年龄必须在15到60岁之间")
    //private int age;

    /**
     * 生日
     */
    // 如果是空，则不校验，如果不为空，则校验
    //@Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$", message = "出生日期格式不正确")
    @JsonFormat(pattern ="yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "生日必须是一个过去的日期")
    private Date birthday;

    /***
     * 手机号码
     * 移动号段: 134 135 136 137 138 139 147 148 150 151 152 157 158 159 165 172 178 182 183 184 187 188 198
     * 联通号段: 130 131 132 145 146 155 156 166 170 171 175 176 185 186
     * 电信号段: 133 149 153 170 173 174 177 180 181 189 191 199
     * 虚拟运营商: 170
     */
    @Pattern(regexp = "^((13[0-9])|(14[5,6,7,9])|(15[^4])|(16[5,6])|(17[0-9])|(18[0-9])|(19[1,8,9]))\\d{8}$", message = "无效的电话号码")
    private String phone;


    /**
     * 性别 1表示男，2表示女
     */
    private String sex;

    /**
    * 头像对应图片的URL
    */
    private String avatar;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Length(min = 6,max = 18,message = "密码长度必须在6位到18位之间")
    //@Pattern(regexp = "[a-z0-9_\\-]{1,100}", message = "仅小写字母（a-z）、数字、破折号（-）和下划线（_）可以使用，且长度最长为100，最短为1")
    private String password;

    /**
     * 邮箱
     */
    @Email(message = "必须是合法的邮箱地址")
    private String email;

    /**
     * 上次修改密码时间
     */
    @TableField("last_pwd_modified_time")
    private Date lastPwdModifiedTime;

    /**
     * 状态 1启用，0禁用
     */
    private String status;
    /**
     * erp账号标识 1表示是erp账号，0表示不是erp账号
     */
    @TableField("erp_flag")
    private String erpFlag;


    /**
     * 构造函数
     * @param account
     * @param name
     * @param email
     */
    public User(String account, String name, String email) {
        this.account = account;
        this.name = name;
        this.email = email;
    }

    /**
     * 构造函数
     * @param id
     * @param account
     * @param name
     * @param email
     */
    public User(Long id,String account, String name, String email) {
        this.id =id;
        this.account = account;
        this.name = name;
        this.email = email;
    }

    /**
     * 实体类继承Model类，必须重写pkVal方法
     * 重写这个方法，return当前类的主键
     * peotected 访问修饰符
     * @return
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
