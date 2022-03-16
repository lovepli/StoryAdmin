package com.story.storyadmin.designPrinciples.singleResponsibilityPrinciple.extendChange1;

import com.story.storyadmin.designPrinciples.singleResponsibilityPrinciple.extendChange1.LiveCourse;
import com.story.storyadmin.designPrinciples.singleResponsibilityPrinciple.extendChange1.ReplayCourse;

/**
 * @author: lipan
 * @date: 2021/8/19
 * @description:
 */
public class Test2 {

    public static void main(String[] args) {
        LiveCourse course = new LiveCourse();
        course.study("直播课");

        ReplayCourse replayCourse=new ReplayCourse();
        replayCourse.study("录播课");
    }
}
