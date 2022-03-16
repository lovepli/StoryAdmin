package com.story.storyadmin.copybean.beanUtilsTest.demo2;

import lombok.Data;

import java.util.List;

/**
 * @author: lipan
 * @date: 11:46 下午
 * @description:
 */
@Data
public class Class {
    private People[] member;
    private People teacher;
    private List<People> student;
}
