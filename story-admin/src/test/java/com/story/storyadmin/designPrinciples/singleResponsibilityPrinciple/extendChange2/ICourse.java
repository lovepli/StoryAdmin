package com.story.storyadmin.designPrinciples.singleResponsibilityPrinciple.extendChange2;

/**
 * @author: lipan
 * @date: 2021/8/19
 * @description:  接口单一职责
 * 当业务继续发展时，要对课程做权限。没有付费的学员可以获得课程的基本信息，已经付费的学员可以获得视频流，即学习权限。
 *
 * 那么对于控制课程层面，至少有两个职责。我们可以把展示职责和管理职责分离开，都实现同一个抽象依赖。
 *
 * 设计一个顶层接口，创建ICourse接口。
 */
public interface ICourse {
    //获得课程的基本信息
    String getCourseName();

    //获取视频流
    byte[] getCourseVioeo();

    //学习课程
    void studyCourse();

    //退款
    void refundCourse();
}
