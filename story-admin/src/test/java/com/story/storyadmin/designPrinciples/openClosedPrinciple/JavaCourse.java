package com.story.storyadmin.designPrinciples.openClosedPrinciple;

import java.math.BigDecimal;

/**
 * @author: lipan
 * @date: 2021/8/18
 * @description:
 */
//整个课程生态有Java架构、大数据、人工智能、前端、软件测试等。
//我们创建一个Java架构课程的类JavaCourse。
public class JavaCourse implements ICourse {

    @Override
    public String getCourseName() {
        return "JAVA课程";
    }

    @Override
    public Integer getCourseId() {
        return 1;
    }


    @Override
    public BigDecimal getCoursePrice() {
        return new BigDecimal("599");
    }
}
