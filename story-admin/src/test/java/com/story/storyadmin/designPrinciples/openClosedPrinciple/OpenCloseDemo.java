package com.story.storyadmin.designPrinciples.openClosedPrinciple;

import com.story.storyadmin.designPrinciples.openClosedPrinciple.extendChange.JavaDiscountCourse;

import java.math.BigDecimal;

/**
 * @author: lipan
 * @date: 2021/8/18
 * @description: 开闭原则
 */
public class OpenCloseDemo {

    public void fun(){
        ICourse course = new JavaCourse();// 多态
        System.out.println("课程ID=" + course.getCourseId());
        System.out.println("课程名称=" + course.getCourseName());
        System.out.println("课程价格=" + course.getCoursePrice());
    }

    /**
     * 打折优惠活动
     */
    public void funExtend(){
        JavaCourse course = new JavaDiscountCourse();// 多态
        JavaDiscountCourse discountJavaCourse = (JavaDiscountCourse) course;
        System.out.println("课程ID=" + course.getCourseId());
        System.out.println("课程名称=" + course.getCourseName());
        System.out.println("课程价格=" + course.getCoursePrice());
        //打折活动
        BigDecimal discount = new BigDecimal(0.5);
        System.out.println("课程折后价=" + discountJavaCourse.getDiscountCoursePrice(discount));
    }

    public static void main(String[] args) {
        OpenCloseDemo openCloseDemo=new OpenCloseDemo();
        openCloseDemo.funExtend();
    }
}
