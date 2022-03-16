package com.story.storyadmin.domain.entity.children;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author: 59688
 * @date: 2021/7/6
 * @description: 课程对象
 */
@Data
@ToString
public class CourseDTO4 extends DCourse2{

    /**
     * DCourse表中有一个teacher_id字段，所以在CourseDTO类中定义一个teacher属性，
     * 用于维护teacher和course之间的一对一关系，通过这个teacher属性就可以知道这个班级是由哪个老师负责的
     */
    private DTeacher teacher;

    // 分数对象
    private DScore scores;

    //使用一个List<DStudent>集合属性表示班级拥有的学生
    private List<DStudent> students;


}
