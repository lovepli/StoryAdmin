package com.story.storyadmin.designPrinciples.DemeterPrinciple;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lipan
 * @date: 2021/8/19
 * @description:
 */
public class TeamLeader{
    public void commandCheckNumber(Employee employee){
        List<Course> courseList = new ArrayList();
        for(int i=0;i<20;i++){
            courseList.add(new Course());
        }
        employee.checkNumberOfCourse(courseList);
    }
}
