package com.story.storyadmin.designPrinciples.dependenceInversionPrinciple.extendChange;

/**
 * @author: lipan
 * @date: 2021/8/18
 * @description:
 * 这时候再看代码，老田的兴趣无论怎么暴涨，对于新的课程，只需要新建一个类，通过传参的方式告诉Tian，而不需要修改底层代码。
 * 实际上，这是一种大家非常熟悉的方式，叫作依赖注入。
 *
 * 构造器注入方式
 * Setter注入方式。
 *
 */
public class Tian1 {

    public void study(ICourse course) {
        course.study();
    }

    public static void main(String[] args) {
        Tian1 tian = new Tian1();
        ICourse course = new JavaCourse();
        tian.study(course);
    }
}
