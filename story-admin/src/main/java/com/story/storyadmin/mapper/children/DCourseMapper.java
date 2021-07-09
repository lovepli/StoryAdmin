package com.story.storyadmin.mapper.children;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.story.storyadmin.domain.entity.children.*;
import com.story.storyadmin.domain.entity.children.dto.CourseTeacherResult;
import com.story.storyadmin.domain.entity.children.manytomany.DCourseBO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: lipan
 * @date: 2021/7/5
 * @description:
 */
@Repository
public interface DCourseMapper extends BaseMapper<DCourse> {

    public List<DCourse> findByPage(Page page, @Param("jsonObject") JSONObject jsonObject);

    CourseDTO getCourse(String id);

    CourseDTO getCourse2(String id);

    CourseDTO getCourse22(String id);

    CourseDTO getCourse24(String id);

    CourseTeacherResult getCourse25(String id);

    CourseDTO2 getCourse3(String id);

    CourseDTO2 getCourse31(String id);



    CourseDTO4 getCourse34(String id);


    CourseDTO3 getCourse4(String id);

    DCourseBO getCourseAndStudentByCourseId(String id);

}
