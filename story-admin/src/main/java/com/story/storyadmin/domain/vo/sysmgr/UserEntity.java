//package com.story.storyadmin.domain.vo.sysmgr;
//
//import com.alibaba.fastjson.annotation.JSONField;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.Data;
//import lombok.ToString;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.Date;
//
///**
// * @author: 59688
// * @date: 2021/7/16
// * @description:  Spring Data JPA 依赖包的注解  使用javax.persistence注解配置PO对象
// */
//@Entity  //说明此java类是实体类
////@Table(name = "st_user")//指定数据库对应的表
//@Data
//@ToString
//public class UserEntity implements  Serializable{
//    /**
//     * 序列号版本
//     */
//    private static final long serialVersionUID = 1L;
//
//    /**
//     * 主键id
//     */
//    @Id  //说明是主键（@Id此注解不能省略，不然Hibernate在调用po进行ORM操作的时候，会出现错误，千万不要认为主键不是自增，就不用指定主键了）
//    // @GeneratedValue(generator = "")  //主键的生成方式
//    @Column(name = "id")  //数据库字段和类属性对应关系
//    private Long id;
//
//
//    /**
//     * 账号
//     */
//    @Column(name = "account")
//    private String account;
//
//    /**
//     * 姓名
//     */
//    @Column(name = "name")
//    private String name;
//
//    /**
//     * 年龄
//     */
//    //@Range(min = 15,max = 60,message = "年龄必须在15到60岁之间")
//    //private int age;
//
//    /**
//     * 生日
//     */
//    @Column(name = "birthday")
//    private Date birthday;
//
//    /***
//     * 手机号码
//     */
//    @Column(name = "phone")
//    private String phone;
//
//
//    /**
//     * 性别 1表示男，2表示女
//     */
//    @Column(name = "sex")
//    private String sex;
//
//    /**
//     * 头像对应图片的URL
//     */
//    @Column(name = "avatar")
//    private String avatar;
//
//    /**
//     * 密码
//     */
//    @Column(name = "password")
//    private String password;
//
//    /**
//     * 邮箱
//     */
//    @Column(name = "email")
//    private String email;
//
//    /**
//     * 上次修改密码时间
//     */
//    @Column(name = "last_pwd_modified_time")
//    private Date lastPwdModifiedTime;
//
//    /**
//     * 状态 1启用，0禁用
//     */
//    @Column(name = "status")
//    private String status;
//    /**
//     * erp账号标识 1表示是erp账号，0表示不是erp账号
//     */
//    @Column(name = "erp_flag")
//    private String erpFlag;
//
//
//
//    /**
//     * 有效标志  1表示有效，0表示无效，无效不会进行物理删除，做的是逻辑删除
//     */
//    @Column(name = "yn_flag")
//    private String ynFlag;
//
//    /**
//     * 创建人 creator
//     */
//    @Column(name = "reator")
//    private String creator;
//
//    /**
//     * 修改人 editor
//     */
//    @Column(name = "editor")
//    private String editor;
//
//    /**
//     * 创建时间
//     */
//    @Column(name = "created_time")
//    private Date createdTime;
//
//    /**
//     * 修改时间
//     */
//    @Column(name = "modified_time")
//    private Date modifiedTime;
//
//    /**
//     * 给不想被序列化的属性增加注解
//     */
//    @Transient // 或者使用 Jackson中的@JsonIgnore注解在返回的json字符串中忽略掉此字段 或者使用@fastJson中的JSONField(serialize=false)
//    //@JsonIgnore
//    //@JSONField(serialize=false)
//       @TableFiled(exit = false)
//    private Integer standby1;//备用字段1
//
//    @Transient
//    private String standby2;//备用字段2
//
//
//}
