package com.story.storyadmin.domain.entity.children;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.story.storyadmin.constant.enumtype.ResultEnum;
import com.story.storyadmin.domain.entity.children.dto.CourseTeacherResult;
import com.story.storyadmin.domain.entity.children.dto.DDeptDTO;
import com.story.storyadmin.domain.entity.children.dto.DDeptDTO2;
import com.story.storyadmin.domain.entity.children.manytomany.DCourseBO;
import com.story.storyadmin.domain.entity.children.manytomany.DStudentBO;
import com.story.storyadmin.domain.entity.children.onetomany.DDeptBO;
import com.story.storyadmin.domain.entity.children.onetomany.DEmployeeBO;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.mapper.children.*;
import com.story.storyadmin.utils.bank.PageUtil;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DCourseAndDeptTest extends TestCase {


    private static final Logger logger = LoggerFactory.getLogger(DCourseAndDeptTest.class);


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
     * ############################################一对一关联查询的五种编写方式#############################################
     * 1、根据课程id查询课程信息，包含老师的信息
     */
    @Test
    public void Test2() {
       CourseDTO course = dCourseMapper.getCourse("0001");
       logger.info("查询课程信息：{}",course.toString());
    }

    @Test
    public void Test21() {
        CourseDTO course = dCourseMapper.getCourse2("0001");
        logger.info("查询课程信息：{}",course.toString());
    }

    @Test
    public void Test22() {
        CourseDTO course = dCourseMapper.getCourse22("0001");
        logger.info("查询课程信息：{}",course.toString());
    }

    @Test
    public void Test24() {
        CourseDTO course = dCourseMapper.getCourse24("0001");
        logger.info("查询课程信息：{}",course.toString());
    }

    @Test
    public void Test25() {
        CourseTeacherResult course = dCourseMapper.getCourse25("0001");
        logger.info("查询课程信息：{}",course.toString());
    }


    /**
     * ############################################一对多关联查询#############################################
     *  根据课程Id查询对应的课程信息,包括学生,老师
     */
    @Test
    public void Test3() {
        CourseDTO2 course = dCourseMapper.getCourse3("0001");
        logger.info("查询课程信息，包含该教师和学生信息：{}",course.toString());
    }

    /**
     * 根据课程Id查询对应的课程信息,包括学生,分数,老师（对象没有继承关系）
     *  TODO 查询结果没有显示出教师信息
     */
    @Test
    public void Test311() {
        CourseDTO2 course = dCourseMapper.getCourse31("0001");
        System.out.println(course.toString());
    }

    /**
     * 根据课程Id查询对应的课程信息,包括学生,分数,老师（对象有继承关系）
     */
    @Test
    public void Test34() {
        CourseDTO4 course = dCourseMapper.getCourse34("0001");
        logger.info("查询班级信息：{}",course.toString());
    }

    /**
     * TODO 执行报错
     */
    @Test
    public void Test31() {
        CourseDTO3 course = dCourseMapper.getCourse4("0001");
        logger.info("查询班级信息：{}",course.toString());
    }

    /**
     * ###########################################多对多关联查询############################################
     * 根据课程id查询课程信息以及这个课程的所有学生信息
     */
    @Test
    public void TestManyToMany() {
        // 一个课程多个学生
        DCourseBO courseStudent = dCourseMapper.getCourseAndStudentByCourseId("0001");
        logger.info("查询课程学生信息：{}",courseStudent.toString());
    }

    /**
     * 根据学生编号查询学生信息以及学生所有课程信息
     */
    @Test
    public void TestManyToMany2() {
        // 一个学生多个课程
        DStudentBO studentCourse = dStudentMapper.getStudentAndCourseByStudentId("0001");
        logger.info("查询学生课程信息：{}",studentCourse.toString());
    }


    /**
     * Mybatis中使用association及collection进行一对多双向关联示例
     * 查询部门信息，包含部门下所有员工信息
     */
    @Test
    public void TestOneToMany() {
        // 一对多
        DDeptBO deptBO = dDeptMapper.selectDeptEmployeeByDeptId("0001");
        logger.info("查询部门信息：{}",deptBO.toString());
    }

    /**
     * 查询员工信息，包含员工所在部门信息
     */
    @Test
    public void TestOneToMany2() {
       // List<DEmployeeBO> employeeBO = dEmployeeMapper.selectEmployeeDeptByDeptId("0001");
        //一对一
         DEmployeeBO employeeBO = dEmployeeMapper.selectEmployeeDeptById("0001");
        logger.info("查询员工信息：{}",employeeBO.toString());
    }



    /**
     * Mybatis中使用association及collection进行自关联
     */
    @Test
    public void Test5() {
        DDeptDTO deptBO = dDeptMapper.selectDeptById2("0003");
        logger.info("根据id查询部门以及下级部门信息：{}",deptBO.toString());
    }

    /**
     * 树形结构展示部门数据TreeUtil
     */
    @Test
    public void Test5_1() {

    }

    /**
     * springboot + mybatis plus + mysql 树形结构查询
     * https://mp.weixin.qq.com/s/0e5BJxfaDo1_fyCN67AWWw
     */
    @Test
    public void Test5_2() {
        List<DDeptPOJO> dDepts=dDeptMapper.noteTree();
       // logger.info("查询所有部门以及下级部门信息：{}",dDepts.toString());
        //log.info(JSONUtil.toJsonPrettyStr(dDeptMapper.noteTree()));
        dDepts.forEach(System.out::println);
    }



    /**
     * 一对多分页的SQL
     * 查询部门下的所有员工信息
     * 部门--员工
     */
    @Test
    public void Test6() {
        JSONObject jsonObject=new JSONObject();
        Page<DDeptDTO2> page =new PageUtil<DDeptDTO2>().getPage(jsonObject);
        List<DDeptDTO2>  deptList = dDeptMapper.findByPage3(page);
        //封装查询list集合到page对象，返回给前端
        page.setRecords(deptList);
        page.setTotal(deptList.size());
        // 封装分页查询数据到返回对象
        Result result = new Result();
        result.setData(page);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());


        //logger.info("分页查询部门以及部门下的所有员工的名字：{}",deptList.toString());
        //deptList.forEach(System.out::println);

        //DDeptDTO2(deptId=0, deptName=公司总部, employeeNames=[])
        //DDeptDTO2(deptId=0001, deptName=财务部, employeeNames=[张三, 李四])
        //DDeptDTO2(deptId=00010003, deptName=研发一组, employeeNames=[])
        //DDeptDTO2(deptId=0002, deptName=人事部, employeeNames=[王五, 赵六])
        //DDeptDTO2(deptId=00020003, deptName=研发二组, employeeNames=[])
        //DDeptDTO2(deptId=0003, deptName=研发部, employeeNames=[])
    }

    @Test
    public void Test7() {
        Page<DDeptDTO2> page = new Page<>(0,10);
        List<DDeptDTO2>  deptList = dDeptMapper.findByPage4(0,10);
        //封装查询list集合到page对象，返回给前端
        page.setRecords(deptList);
        page.setTotal(deptList.size());
        // 封装分页查询数据到返回对象
        Result result = new Result();
        result.setData(page);
        result.setResult(true);
        result.setCode(ResultEnum.TOKEN_CHECK_SUCCESS.getCode());


        //logger.info("分页查询部门以及部门下的所有员工的名字：{}",deptList.toString());
        //deptList.forEach(System.out::println);

        //DDeptDTO2(deptId=0001, deptName=财务部, employeeNames=[张三, 李四])
        //DDeptDTO2(deptId=0002, deptName=人事部, employeeNames=[王五, 赵六])
        //DDeptDTO2(deptId=0, deptName=公司总部, employeeNames=[])
        //DDeptDTO2(deptId=00010003, deptName=研发一组, employeeNames=[])
        //DDeptDTO2(deptId=00020003, deptName=研发二组, employeeNames=[])
        //DDeptDTO2(deptId=0003, deptName=研发部, employeeNames=[])
    }












}