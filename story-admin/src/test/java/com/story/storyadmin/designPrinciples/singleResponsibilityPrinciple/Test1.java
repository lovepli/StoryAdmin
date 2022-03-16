package com.story.storyadmin.designPrinciples.singleResponsibilityPrinciple;

/**
 * @author: lipan
 * @date: 2021/8/19
 * @description:
 */
public class Test1 {

    public static void main(String[] args) {
        Course course = new Course();
        course.study("直播课");
        course.study("看录像");
    }
}
