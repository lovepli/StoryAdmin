package com.story.storyadmin.framework.mybatisDemo.mybatis2;


/**
 * @author: 59688
 * @date: 2021/9/28
 * @description: 透彻理解MyBatis设计思想之手写实现 https://www.jianshu.com/p/73ee8caddc68
 */
public class Test {

    public static void main(String[] args) {

    start();

    }

    private static void start(){
        MySqlSession sqlsession = new MyDefaultSqlSession();

        StudentMapper studentMapper = sqlsession.getMapper(StudentMapper.class);

        Student student =studentMapper.findStudentById(1);
        System.out.println(student);
    }
}
