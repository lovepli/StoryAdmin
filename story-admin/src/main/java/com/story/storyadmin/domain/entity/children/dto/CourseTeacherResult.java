package com.story.storyadmin.domain.entity.children.dto;


import com.story.storyadmin.domain.entity.children.DTeacher;

/**
 * @author: 59688
 * @date: 2021/7/7
 * @description: 一对一结果对象 CourseTeacherResult
 */
@SuppressWarnings("unused")
public class CourseTeacherResult extends CourseDO{

    private String teacherId;

    private String teacherName;

    public CourseTeacherResult() {
        super();
        //即时实例化关联对象
        super.setTeacher(new DTeacher());
    }

    @Override
    public void setId(String teacherId) {
        this.teacherId = teacherId;
        //设置
        super.getTeacher().setId(teacherId);
    }

    @Override
    public void setName(String teacherName) {
        this.teacherName = teacherName;
        //设置
        super.getTeacher().setName(teacherName);
    }




}
