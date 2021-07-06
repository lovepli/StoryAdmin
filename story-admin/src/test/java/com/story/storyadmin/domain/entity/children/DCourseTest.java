package com.story.storyadmin.domain.entity.children;

import com.story.storyadmin.domain.entity.sysmgr.User;
import com.story.storyadmin.domain.entity.sysmgr.UserTest;
import com.story.storyadmin.mapper.children.DCourseMapper;
import com.story.storyadmin.mapper.sysmgr.UserMapper;
import com.story.storyadmin.service.sysmgr.UserService;
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
        // 查询出所有数据
        CourseDTO course = dCourseMapper.getCourse2("0001");
        logger.info("查询班级信息：{}",course.toString());
    }

    /**
     * 一对多关联查询
     *  根据班级Id查询对应的班级信息,包括学生,老师
     */
    @Test
    public void Test3() {
        // 查询出所有数据
        CourseDTO2 course = dCourseMapper.getCourse3("0001");
        logger.info("查询班级信息：{}",course.toString());
    }

    @Test
    public void Test33() {
        // 查询出所有数据
        CourseDTO3 course = dCourseMapper.getCourse33("0001");
        logger.info("查询班级信息：{}",course.toString());
    }

    @Test
    public void Test31() {
        // 查询出所有数据
        CourseDTO3 course = dCourseMapper.getCourse4("0001");
        logger.info("查询班级信息：{}",course.toString());
    }



}