package com.story.storyadmin.domain.entity.children;

import com.story.storyadmin.mapper.children.DEmployeeMapper;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * @author: lipan
 * @date: 2021年09月15日 20:36 下午
 * @description: 经典mysql 薪资问题51题目
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmpAndDeptTest extends TestCase {

    private static final Logger logger = LoggerFactory.getLogger(EmpAndDeptTest.class);


    @Autowired
    DEmployeeMapper dEmployeeMapper;

    /**
     *  1. 取得每个部门最高薪水的人员名称
     */
    @Test
    public void Test1() {
        List<Map<String, Object>> list= dEmployeeMapper.findMaxSalaryGroupByDeptId();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }

    /**
     * 2. 哪些人的薪水在部门平均薪水之上
     */
    @Test
    public void Test2() {
        List<Map<String, Object>> list= dEmployeeMapper.findEmpInfoMinSalary();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }

    /**
     *3. 取得部门中（所有人）平均薪水等级
     */
    @Test
    public void Test3() {
        List<Map<String, Object>> list= dEmployeeMapper.findAllEmpInfosMinSalary();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }

    /**
     *4. 不准用组函数（max），取得最高薪水（给出两种解决方案）
     */
    @Test
    public void Test4() {
        List<Map<String, Object>> list= dEmployeeMapper.findMinSalary();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }

    /**
     *5. 取得平均薪水最高的部门的部门编号
     */
    @Test
    public void Test5() {
        List<Map<String, Object>> list= dEmployeeMapper.findDeptIdByAvgSalary();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }

    /**
     *6. 取得平均薪水最高的部门的部门名称
     */
    @Test
    public void Test6() {
        List<Map<String, Object>> list= dEmployeeMapper.findDeptNameByAvgSalaryGroup();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }


    /**
     *7. 求平均薪水的等级最低的部门的部门名称
     */
    @Test
    public void Test7() {
        List<Map<String, Object>> list= dEmployeeMapper.findDeptNameByAvgSalaryGrade();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }

    /**
     *8. 取得比普通员工（员工编号没有在manager_id列上出现的）的最高薪水还要高的经理人姓名
     */
    @Test
    public void Test8() {
        List<Map<String, Object>> list= dEmployeeMapper.findManagerNameByMaxSalary();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }

    /**
     *9. 取得薪水最高的前五名员工
     */
    @Test
    public void Test9() {
        List<Map<String, Object>> list= dEmployeeMapper.findMaxSalaryFiveEmp();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }


    /**
     *10. 取得薪水最高的第六到第十名员工
     */
    @Test
    public void Test10() {
        List<Map<String, Object>> list= dEmployeeMapper.findMaxSalarySixToTenEmp();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }

    /**
     *11. 取得最后入职的5名员工
     */
    @Test
    public void Test11() {
        List<Map<String, Object>> list= dEmployeeMapper.findLastHireDateFiveEmp();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }


    /**
     *12. 取得每个薪水等级有多少员工
     */
    @Test
    public void Test12() {
        List<Map<String, Object>> list= dEmployeeMapper.findEmpCountBySalaryGrade();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }


    /**
     *14. 列出所有员工及领导的名字 TODO 左连接查询
     */
    @Test
    public void Test13() {
        List<Map<String, Object>> list= dEmployeeMapper.findEmpAndLeaderName();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });

    }


    /**
     *15. 列出受雇日期早于其直接上级的所有员工编号、姓名、部门名称
     */
    @Test
    public void Test14() {
        List<Map<String, Object>> list= dEmployeeMapper.findEmpInfoByHireDate();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }

    /**
     *16. 列出部门名称和这些部门的员工信息，同时列出那些没有员工的部门
     */
    @Test
    public void Test15() {
        List<Map<String, Object>> list= dEmployeeMapper.findDeptInfoAndEmpInfo();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }
    /**
     *17. 列出至少有5个员工的所有部门
     */
    @Test
    public void Test16() {
        List<Map<String, Object>> list= dEmployeeMapper.findDeptInfoByEmpNum();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }

    /**
     *18. 列出薪水比“张三”多的所有员工信息
     */
    @Test
    public void Test17() {
        List<Map<String, Object>> list= dEmployeeMapper.findEmpInfoBySalary();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }
    /**
     *19. 列出所有“软件工程师”的姓名及其部门名称，部门人数
     */
    @Test
    public void Test18() {
        List<Map<String, Object>> list= dEmployeeMapper.findDeptNameAndNumByJobName();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }
    /**
     *20. 列出最低薪水大于1500的各种工作及从事此工作的全部雇员人数
     */
    @Test
    public void Test19() {
        List<Map<String, Object>> list= dEmployeeMapper.findJobNameAndEmpNum();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }
    /**
     *21. 列出在部门“财务部”工作的员工的姓名，假定不知道财务部门的部门编号
     */
    @Test
    public void Test20() {
        List<Map<String, Object>> list= dEmployeeMapper.findEmpNameByDeptName();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }



    /**
     * 211.查询所有的员工的工资等级 TODO
     */
    @Test
    public void Test211() {
        List<Map<String, Object>> list= dEmployeeMapper.findAllEmpyInfoAndSalaryGrade();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });

    }
    /**
     * 222. 查询所有的上级领导 关联子查询 TODO
     */
    @Test
    public void Test212() {
        List<Map<String, Object>> list= dEmployeeMapper.findAllManagers2();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }

    /**
     * TODO 注意分析题目。理清楚题目的sql执行顺序，这是一道经典的多表关联查询！！！
     * 22. 列出薪金高于公司平均薪金的所有员工，所在部门、上级领导、雇员的工资等级
     */
    @Test
    public void Test21() {

        List<Map<String, Object>> list= dEmployeeMapper.findEmpInfoAndDeptInfoBySalary();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });

    }

    /**
     *23. 列出与“张三”从事相同工作的所有员工及部门名称
     */
    @Test
    public void Test22() {
        List<Map<String, Object>> list= dEmployeeMapper.findEmpInfoAndDeptInfoByEmpName();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });

    }
    /**
     *24. 列出薪金等于部门0003中员工的薪金的其它员工的姓名和薪金
     */
    @Test
    public void Test23() {

        List<Map<String, Object>> list= dEmployeeMapper.findEmpInfoAndSalaryByDeptId();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }
    /**
     *25. 列出薪金高于在部门0003工作的所有员工的薪金的员工姓名和薪金、部门名称
     */
    @Test
    public void Test24() {
        List<Map<String, Object>> list= dEmployeeMapper.findEmpInfoAndDeptInfoAndSalaryByDeptId();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }
    /**
     *26. 列出在每个部门工作的员工数量、平均工资和平均服务期限（*） TODO 其中涉及的函数包括：ifnull(字段,0)      datediff(now(),日期)      right join
     */
    @Test
    public void Test25() {
        List<Map<String, Object>> list= dEmployeeMapper.findEmpNumberAndAvgSalaryAndWoekDateByDeptId();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });

    }
    /**
     *27. 列出所有员工的姓名、部门名称和工资
     */
    @Test
    public void Test26() {
        List<Map<String, Object>> list= dEmployeeMapper.findAllEmpNameAndDeptNameAndSalary();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });

    }
    /**
     *28. 列出所有部门的详细信息和人数
     */
    @Test
    public void Test27() {

        List<Map<String, Object>> list= dEmployeeMapper.findAllDeptInfoAndCountNumber();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }
    /**
     *29. 列出各种工作的最低工资及从事此工作的雇员姓名
     */
    @Test
    public void Test28() {
        List<Map<String, Object>> list= dEmployeeMapper.findMinSalaryAndEmpName();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }
    /**
     *30. 列出各个部门经理的最低薪金
     */
    @Test
    public void Test29() {

        List<Map<String, Object>> list= dEmployeeMapper.findManagerMinSalary();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }
    /**
     *31. 列出所有员工的年工资，按年薪从低到高掋序
     */
    @Test
    public void Test30() {

        List<Map<String, Object>> list= dEmployeeMapper.findAllEmpSalaryOrderByYeaySalary();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }
    /**
     *32. 求出员工领导的薪水超过3000的员工名称和领导名称 TODO 经典的自连接查询
     */
    @Test
    public void Test31() {

        List<Map<String, Object>> list= dEmployeeMapper.findEmpNanmeAndManagerNameByEmpManagerSalary();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }
    /**
     *33. 求部门名称中带“S”字符的部门员工的工资合计、部门人数
     */
    @Test
    public void Test32() {

        List<Map<String, Object>> list= dEmployeeMapper.findDeptSunmSalaryByEmpName();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }
    /**
     *34. 给任职日期超过30年的员工加薪10%
     */
    @Test
    public void Test33() {
      Integer i=  dEmployeeMapper.updateSalaryByHireDate();
    }
    /**
     *35.查询部门编号为0002,0003的两个部门所有员工的信息
     */
    @Test
    public void Test34() {

        List<Map<String, Object>> list= dEmployeeMapper.findAllEmpInfoByDdptId();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }
    /**
     *36.查询工资大于0001号员工工资的员工的信息
     */
    @Test
    public void Test35() {
        List<Map<String, Object>> list= dEmployeeMapper.findAllEmpInfoByEmpSalaryAndId();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }
    /**
     *37.查询与0001号或0002号员工的manager和dept_id相同的其他员工的e_id, manager, dept_id
     * TODO  TODO 多列子查询（不成对比较 & 成对比较）
     */
    @Test
    public void Test36() {
        List<Map<String, Object>> list= dEmployeeMapper.findAllEmpInfoByManagerIdAndDeptId();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }
    /**
     *38.查询所有部门的平均薪资，取最大的一个薪资
     */
    @Test
    public void Test37() {

        List<Map<String, Object>> list= dEmployeeMapper.findMaxSalaryByDeptId();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }
    /**
     *39.返回比本部门平均工资高的员工的e_name, dept_id salary及平均工资
     */
    @Test
    public void Test38() {

        List<Map<String, Object>> list= dEmployeeMapper.findSalaryInfoAndEmpInfoByDeptId();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }
    /**
     *40.若部门为0003 查看工资的 1.1 倍，部门号为 0002 工资的1.2倍，其余 1.3 倍
     * TODO case...when ... then... when ... then ... else ... end
     */
    @Test
    public void Test39() {
        List<Map<String, Object>> list= dEmployeeMapper.findSalaryInfoByDeptId();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });

    }
    /**
     * 41. 显式员工的e_id,e_name和address。其中，若员工dept_id与address为'南山区'的dept_id相同，则address为’南山科技园666’,其余则为’其他地区777’
     * TODO
     */
    @Test
    public void Test40() {

        List<Map<String, Object>> list= dEmployeeMapper.findEmpInfoAndAddressByAdddress();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }
    /**
     *42.查询员工的empno,ename,要求按照员工的dname排序
     */
    @Test
    public void Test41() {

        List<Map<String, Object>> list= dEmployeeMapper.findEmpIdAndNameOrderByDeptName();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }
    /**
     *43.查询公司管理者的e_id,e_name,job_name,dept_id信息
     */
    @Test
    public void Test42() {

        List<Map<String, Object>> list= dEmployeeMapper.findManagerInfo();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }
    /**
     *44.查询dept表中，不存在于emp表中的部门的deptno和dname
     */
    @Test
    public void Test43() {
        List<Map<String, Object>> list= dEmployeeMapper.findEmpIdAndNameNotIn();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });

    }
    /**
     *45.更改 0001 员工的信息: 使其工资变为所在部门中的最高工资, job_name 变为公司中平均工资最低的 job_name
     */
    @Test
    public void Test44() {
        Integer i=  dEmployeeMapper.updateEmpById();

    }
    /**
     *46.删除 108 号员工所在部门中工资最低的那个员工
     */
    @Test
    public void Test45() {
        Integer i=  dEmployeeMapper.deleteEmpById();
    }
    /**
     *47. 查询公司中各部门的总工资大于公司中各部门的平均总工资的部门信息
     */
    @Test
    public void Test46() {

    }
    /**
     *48.查询员工的e_name, dept_id, salary,其中员工的salary,dept_id与有奖金的任何一个员工的salary,dept_id相同即可
     */
    @Test
    public void Test47() {
        List<Map<String, Object>> list= dEmployeeMapper.findEmpInfoByCommission();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }
    /**
     *49.选择工资大于所有job_name为'软件工程师'的员工的工资的员工e_name, job_name, salary
     */
    @Test
    public void Test48() {

        List<Map<String, Object>> list= dEmployeeMapper.findEmpInfoByJobName();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }

    /**
     *50.选择所有没有管理者的员工的e_name
     * TODO 内联子查询
     */
    @Test
    public void Test49() {

        List<Map<String, Object>> list= dEmployeeMapper.findEmpNameByManagerId();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }

    /**
     *51. 查询0001，0002，0003号部门的job_name，dept_id并且dept_id按0001，0002，0003的顺序排列
     * TODO union的使用，并排序
     */
    @Test
    public void Test50() {
        List<Map<String, Object>> list= dEmployeeMapper.findJobNameAndDeptIdByDeptId();
        list.forEach((v) ->{
            v.forEach((k,l) ->{
                System.out.println("key="+k+"; value="+l.toString());
            });
            System.out.println("-------------------------------");
        });
    }






}
