package com.story.storyadmin.designPrinciples.singleResponsibilityPrinciple.extendChange1;

/**
 * @author: lipan
 * @date: 2021/8/19
 * @description:  类单一职责
 * LiveCourse直播课程
 * 从上面代码来看，Course类承担了两种处理逻辑。
 *
 * 假如，现在对课程进行加密，那么直播课和录播课的加密逻辑是不一样的，必须修改代码。
 *
 * 而修改代码逻辑势必会相互影响，容易带来不可控的风险。
 *
 * 我们对职责进行分离解耦，分别创建两个类LiveCourse和ReplayCourse。
 */
public class LiveCourse {
    public void study(String courseName){
        System.out.println("现场直播，无法修改播放速度");
    }
}
