package com.story.storyadmin.designPrinciples.dependenceInversionPrinciple.extendChange;


/**
 * @author: lipan
 * @date: 2021/8/18
 * @description:
 */
public class Tian2 {

    private ICourse course;

    /** 构造函数方式注入course **/
    public Tian2(ICourse course) {
        this.course = course;
    }

    public void study() {
        course.study();
    }

    public static void main(String[] args) {
        Tian2 tian = new Tian2(new JavaCourse());
        tian.study();
    }
}

