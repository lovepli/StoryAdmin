package com.story.storyadmin.设计模式.原型模式;

import lombok.Data;

/**
 * @author: lipan
 * @date: 2021/8/23
 * @description: 返回给前端或者调用方的UserVO实体类。
 */
@Data
public class UserVO {
    private Long id;
    private String name;
    private Integer age;
    //....可能还有很多属性
    //省略getter setter
}