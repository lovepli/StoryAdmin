package com.story.storyadmin.designPrinciples.singleResponsibilityPrinciple.extendChange2;

/**
 * @author: lipan
 * @date: 2021/8/19
 * @description:
 * 可以把这个接口拆成两个接口，创建一个接口ICourseInfo和ICourseManager。ICourseInfo接口的代码如下。
 *
 * ICourseInfo接口的代码如下：
 */
public interface ICourseInfo {
    //获取课程名称
    String getCourseName();
    //获取课程视频流
    byte [] getCourseVideo();
}
