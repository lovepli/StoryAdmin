package com.story.storyadmin.domain.entity.sysmgr;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.story.storyadmin.domain.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@EqualsAndHashCode(callSuper = true) //@EqualsAndHashCode 和@Data注解的使用说明 https://blog.csdn.net/zhanlanmg/article/details/50392266
@Accessors(chain = true) //chain的中文含义是链式的，设置为true，则setter方法返回当前对象
@TableName("st_user")
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
     * 账号
     */
    private String account;

    /**
     * 姓名
     */
    private String name;

    /**
    * 头像对应图片的URL
    */
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
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
     */
    public User(){}

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
