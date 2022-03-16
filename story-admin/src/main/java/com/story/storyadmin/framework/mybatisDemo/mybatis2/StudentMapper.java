package com.story.storyadmin.framework.mybatisDemo.mybatis2;

/**
 * @author: 59688
 * @date: 2021/9/28
 * @description: 这里为了简化处理，在RequestMapping这块硬编码了。
 */
public interface StudentMapper {

    public Student findStudentById(int id);
    public void insertStudent(Student student);
}
