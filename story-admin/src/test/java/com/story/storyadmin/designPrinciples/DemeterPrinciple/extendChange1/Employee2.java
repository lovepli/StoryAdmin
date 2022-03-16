package com.story.storyadmin.designPrinciples.DemeterPrinciple.extendChange1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lipan
 * @date: 2021/8/19
 * @description:
 */
public class Employee2 {
    public void checkNumberOfCourse(){
        List<Course2> courseList = new ArrayList<>();
        for(int i=0;i<20;i++){
            courseList.add(new Course2());
        }
        System.out.println("目前已经发布的课程数量是：" + courseList.size());
    }
}
