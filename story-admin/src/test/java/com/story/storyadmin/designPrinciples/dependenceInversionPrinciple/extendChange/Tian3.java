package com.story.storyadmin.designPrinciples.dependenceInversionPrinciple.extendChange;

/**
 * @author: lipan
 * @date: 2021/8/18
 * @description:
 * 根据构造器注入方式，当调用时，每次都要创建实例。
 *
 * 但，如果Tian是全局单例，则只能选择Setter注入方式，继续修改Tian类的代码。
 */
public class Tian3 {

    private ICourse course;

    public void setCourse(ICourse course) {
        this.course = course;
    }

    public void study() {
        course.study();
    }

    public static void main(String[] args) {
        Tian3 tian = new Tian3();
        tian.setCourse(new JavaCourse());
        tian.study();

        tian.setCourse(new PythonCourse());
        tian.study();
    }
}
