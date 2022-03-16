package com.story.storyadmin.designPrinciples.openClosedPrinciple;

import java.math.BigDecimal;

/**
 * @author: lipan
 * @date: 2021/8/18
 * @description: 报名一个网上课程，课程有价格、id、名称。
 */
//课程接口类
public interface ICourse {

    /**
     * 获取课程名称
     * @return
     */
    String getCourseName();

    /**
     * 获取课程ID
     * @return
     */
    Integer getCourseId();

    /**
     * 获取课程价格
     * @return
     */
    BigDecimal getCoursePrice();
}
