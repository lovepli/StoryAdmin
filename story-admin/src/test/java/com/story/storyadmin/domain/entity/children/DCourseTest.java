package com.story.storyadmin.domain.entity.children;

import com.story.storyadmin.domain.entity.children.dto.CourseTeacherResult;
import com.story.storyadmin.domain.entity.children.dto.DDeptDTO;
import com.story.storyadmin.domain.entity.children.manytomany.DCourseBO;
import com.story.storyadmin.domain.entity.children.manytomany.DStudentBO;
import com.story.storyadmin.domain.entity.children.onetomany.DDeptBO;
import com.story.storyadmin.domain.entity.children.onetomany.DEmployeeBO;
import com.story.storyadmin.mapper.children.DCourseMapper;
import com.story.storyadmin.mapper.children.DDeptMapper;
import com.story.storyadmin.mapper.children.DEmployeeMapper;
import com.story.storyadmin.mapper.children.DStudentMapper;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
public class DCourseTest extends TestCase {


    private static final Logger logger = LoggerFactory.getLogger(DCourseTest.class);


    @Autowired
    DCourseMapper dCourseMapper;

    @Autowired
    DStudentMapper dStudentMapper;

    @Autowired
    DDeptMapper dDeptMapper;

    @Autowired
    DEmployeeMapper dEmployeeMapper;


    @Test
    public void Test1() {
        // 查询出所有数据
        List<DCourse> list = dCourseMapper.selectList(null);
        list.forEach(System.out::println);
    }

    /**
     * 一对一关联查询
     * 1、根据班级id查询班级信息(带老师的信息)
     */
    @Test
    public void Test2() {
        // 查询出所有数据
       CourseDTO course = dCourseMapper.getCourse("0001");
       logger.info("查询班级信息：{}",course.toString());
    }

    @Test
    public void Test21() {
        CourseDTO course = dCourseMapper.getCourse2("0001");
        logger.info("查询班级信息：{}",course.toString());
    }

    @Test
    public void Test22() {
        CourseDTO course = dCourseMapper.getCourse22("0001");
        logger.info("查询班级信息：{}",course.toString());
    }

    @Test
    public void Test24() {
        // 查询出所有数据
        CourseDTO course = dCourseMapper.getCourse24("0001");
        logger.info("查询班级信息：{}",course.toString());
    }

    @Test
    public void Test25() {
        // 查询出所有数据
        CourseTeacherResult course = dCourseMapper.getCourse25("0001");
        logger.info("查询班级信息：{}",course.toString());
    }

    /**
     * 一对多关联查询
     *  根据课程Id查询对应的课程信息,包括学生,老师
     */
    @Test
    public void Test3() {
        // 查询出所有数据
        CourseDTO2 course = dCourseMapper.getCourse3("0001");
        logger.info("查询班级信息：{}",course.toString());
    }

    // 查询结果由问题
    @Test
    public void Test311() {
        // 查询出所有数据
        CourseDTO2 course = dCourseMapper.getCourse31("0001");
       // list.forEach(System.out::println);
        System.out.println(course.toString());
    }

    /**
     * 根据课程Id查询对应的课程信息,包括学生,分数,老师
     */
    @Test
    public void Test34() {
        CourseDTO4 course = dCourseMapper.getCourse34("0001");
        logger.info("查询班级信息：{}",course.toString());
    }

    /**
     * TODO 报错
     */
    @Test
    public void Test31() {

        CourseDTO3 course = dCourseMapper.getCourse4("0001");
        logger.info("查询班级信息：{}",course.toString());
    }


    //#################################多对多关联查询############################################
    @Test
    public void TestManyToMany() {
        // 一个课程多个学生
        DCourseBO courseStudent = dCourseMapper.getCourseAndStudentByCourseId("0001");
        logger.info("查询课程学生信息：{}",courseStudent.toString());
    }

    @Test
    public void TestManyToMany2() {
        // 一个学生多个课程
        DStudentBO studentCourse = dStudentMapper.getStudentAndCourseByStudentId("0001");
        logger.info("查询学生课程信息：{}",studentCourse.toString());
    }


    // Mybatis中使用association及collection进行一对多双向关联示例
    @Test
    public void TestOneToMany() {
        // 一对多
        DDeptBO deptBO = dDeptMapper.selectDeptEmployeeByDeptId("0001");
        logger.info("查询部门信息：{}",deptBO.toString());
    }

    @Test
    public void TestOneToMany2() {
        // 一对一
       // List<DEmployeeBO> employeeBO = dEmployeeMapper.selectEmployeeDeptByDeptId("0001");
         DEmployeeBO employeeBO = dEmployeeMapper.selectEmployeeDeptById("0001");
        logger.info("查询员工信息：{}",employeeBO.toString());
    }

    // Mybatis中使用association及collection进行自关联
    @Test
    public void Test5() {

        DDeptDTO deptBO = dDeptMapper.selectDeptById2("00010003");
        logger.info("根据id查询部门以及下级部门信息：{}",deptBO.toString());
    }









}