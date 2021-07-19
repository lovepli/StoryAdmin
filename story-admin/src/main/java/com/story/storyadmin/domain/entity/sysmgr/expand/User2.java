package com.story.storyadmin.domain.entity.sysmgr.expand;

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
@TableName("st2_user")
public class User2 extends BaseEntity<User2> {

  private static final long serialVersionUID = 1L;

    /**
     * 部门id
     */
    private Long deptId;
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
