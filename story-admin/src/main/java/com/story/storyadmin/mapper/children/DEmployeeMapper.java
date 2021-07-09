package com.story.storyadmin.mapper.children;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.story.storyadmin.domain.entity.children.DEmployee;
import com.story.storyadmin.domain.entity.children.onetomany.DEmployeeBO;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}


