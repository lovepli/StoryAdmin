package com.story.storyadmin.mapper.children;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.story.storyadmin.domain.entity.children.DStudent;
import com.story.storyadmin.domain.entity.children.manytomany.DStudentBO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: lipan
 * @date: 2021/7/5
 * @description:
 */
@Repository
public interface DStudentMapper extends BaseMapper<DStudent> {

    public List<DStudent> findByPage(Page page, @Param("jsonObject") JSONObject jsonObject);

    DStudent getStudent(String id);

    DStudentBO getStudentAndCourseByStudentId(String id);
}
