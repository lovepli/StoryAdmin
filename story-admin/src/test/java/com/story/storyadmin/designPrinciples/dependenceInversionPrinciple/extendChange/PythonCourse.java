package com.story.storyadmin.designPrinciples.dependenceInversionPrinciple.extendChange;

/**
 * @author: lipan
 * @date: 2021/8/18
 * @description:
 */
public class PythonCourse implements ICourse {
    @Override
    public void study() {
        System.out.println("老田在学习Python课程");
    }
}
