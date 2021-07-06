package com.story.storyadmin.domain.entity.children;

import lombok.Data;

/**
 * @author: 59688
 * @date: 2021/7/6
 * @description:  课程对象
 */
@Data
public class CourseDTO {

    //定义实体类的属性，与DCourse表中的字段对应
    private String id;

    private String name;

    /**
     * DCourse表中有一个teacher_id字段，所以在CourseDTO类中定义一个teacher属性，
     * 用于维护teacher和course之间的一对一关系，通过这个teacher属性就可以知道这个课程是由哪个老师负责的
     */
    private DTeacher teacher;

    @Override
   public String toString() {
        return "CourseDTO [id=" + id + ", name=" + name + ", teacher=" + teacher + "]";
    }

}
