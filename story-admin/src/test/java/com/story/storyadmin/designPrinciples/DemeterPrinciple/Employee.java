package com.story.storyadmin.designPrinciples.DemeterPrinciple;

import java.util.List;

/**
 * @author: lipan
 * @date: 2021/8/19
 * @description:
 */
public class Employee {
    public void checkNumberOfCourse(List<Course> courseList) {
        System.out.println("目前已经发布的课程数量是：" + courseList.size());
    }
}

