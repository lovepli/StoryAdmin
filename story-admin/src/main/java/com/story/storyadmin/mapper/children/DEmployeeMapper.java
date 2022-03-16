package com.story.storyadmin.mapper.children;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.story.storyadmin.domain.entity.children.DEmployee;
import com.story.storyadmin.domain.entity.children.onetomany.DEmployeeBO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author: 59688
 * @date: 2021/7/9
 * @description:
 */
@Repository
public interface DEmployeeMapper extends BaseMapper<DEmployee> {

    DEmployee selectEmployeeByDeptId(String id);

    List<DEmployeeBO> selectEmployeeDeptByDeptId(String id);

    DEmployeeBO selectEmployeeDeptById(String id);

    List<String> selectEmployeeNamesBydeptId(String deptId);

    List<Map<String, Object>> findMaxSalaryGroupByDeptId();
    List<Map<String, Object>> findMaxSalaryGroupByDeptId2();

    List<Map<String, Object>> findEmpInfoMinSalary();
    List<Map<String, Object>> findEmpInfoMinSalary2();

    List<Map<String, Object>> findAllEmpInfosMinSalary();

    List<Map<String, Object>> findMinSalary();
    //自连接：其中一个值不小于任何一个值，distinct去重后使用not in 过滤查询结果后即为最大值：
    List<Map<String, Object>> findMinSalary2();

    List<Map<String, Object>> findDeptIdByAvgSalary();
    List<Map<String, Object>> findDeptNameByAvgSalaryGroup();

    List<Map<String, Object>> findDeptNameByAvgSalaryGrade();

    List<Map<String, Object>> findManagerNameByMaxSalary();
    List<Map<String, Object>> findManagerNameByMaxSalary2();

    List<Map<String, Object>> findMaxSalaryFiveEmp();

    List<Map<String, Object>> findMaxSalarySixToTenEmp();

    List<Map<String, Object>> findLastHireDateFiveEmp();

    List<Map<String, Object>> findEmpCountBySalaryGrade();

    List<Map<String, Object>> findEmpAndLeaderName();

    List<Map<String, Object>> findEmpInfoByHireDate();

    List<Map<String, Object>> findDeptInfoAndEmpInfo();

    List<Map<String, Object>> findDeptInfoByEmpNum();

    List<Map<String, Object>> findEmpInfoBySalary();

    List<Map<String, Object>> findDeptNameAndNumByJobName();

    List<Map<String, Object>> findJobNameAndEmpNum();

    List<Map<String, Object>> findEmpNameByDeptName();

    //经典多表关联查询
    List<Map<String, Object>> findEmpInfoAndDeptInfoBySalary();

     //查询所有的上级领导 关联子查询
    List<Map<String, Object>> findAllManagers();
    List<Map<String, Object>> findAllManagers2();

    List<Map<String, Object>> findAllEmpyInfoAndSalaryGrade();

    List<Map<String, Object>> findEmpInfoAndDeptInfoByEmpName();

    List<Map<String, Object>> findEmpInfoAndSalaryByDeptId();

    List<Map<String, Object>> findEmpInfoAndDeptInfoAndSalaryByDeptId();

    List<Map<String, Object>> findEmpNumberAndAvgSalaryAndWoekDateByDeptId();

    List<Map<String, Object>> findAllEmpNameAndDeptNameAndSalary();

    List<Map<String, Object>> findAllDeptInfoAndCountNumber();

    List<Map<String, Object>> findMinSalaryAndEmpName();

    List<Map<String, Object>> findManagerMinSalary();

    List<Map<String, Object>> findAllEmpSalaryOrderByYeaySalary();

    //经典的自连接查询
    List<Map<String, Object>> findEmpNanmeAndManagerNameByEmpManagerSalary();

    List<Map<String, Object>> findDeptSunmSalaryByEmpName();

    Integer  updateSalaryByHireDate();

    List<Map<String, Object>> findAllEmpInfoByDdptId();

    List<Map<String, Object>> findAllEmpInfoByEmpSalaryAndId();

    List<Map<String, Object>> findAllEmpInfoByManagerIdAndDeptId();
    List<Map<String, Object>> findAllEmpInfoByManagerIdAndDeptId2();

    List<Map<String, Object>> findMaxSalaryByDeptId();

    List<Map<String, Object>> findSalaryInfoAndEmpInfoByDeptId();

    List<Map<String, Object>> findSalaryInfoByDeptId();

    List<Map<String, Object>> findEmpInfoAndAddressByAdddress();

    //内联子查询
    List<Map<String, Object>> findEmpIdAndNameOrderByDeptName();

    List<Map<String, Object>> findManagerInfo();

    List<Map<String, Object>> findEmpIdAndNameNotIn();

    Integer updateEmpById();

    Integer deleteEmpById();

    List<Map<String, Object>> findEmpInfoByCommission();

    List<Map<String, Object>> findEmpInfoByJobName();

    List<Map<String, Object>> findEmpNameByManagerId();

    List<Map<String, Object>> findJobNameAndDeptIdByDeptId();

















































}


