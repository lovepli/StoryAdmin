package com.story.storyadmin.mapper.children;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.story.storyadmin.domain.entity.children.DScore;
import com.story.storyadmin.domain.entity.children.DStudent;
import com.story.storyadmin.domain.entity.sysmgr.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.type.Alias;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author: lipan
 * @date: 2021/7/5
 * @description:
 */
@Repository
public interface DScoreMapper extends BaseMapper<DScore> {
    public List<DScore> findByPage(Page page, @Param("jsonObject") JSONObject jsonObject);

    List<Map<String, Integer>> findSumScoreList();

    @MapKey("id")
    Map<String, Map<String,Integer>>   findSumScoreList2();


    List<Map<String, Integer>> findAvgScoreList();

    @MapKey("id")
    Map<String, Map<String,Integer>> findAvgScoreList2();


   List<Map<String, Object>>  findStudentInfoOrderbyScore();

    /**仅仅进行关联查询，数据的筛选交给Java8新特性来处理**/
    List<Map<String, Object>>  findStudentInfoOrderbyScore2();

    List<Map<String, Object>>   findTeacherAndScoreInfoOrderbyScore();

    List<Map<String, Object>>    findStudentAndScoreInfobyCourseName();

    List<Map<String, Object>>    findStudentAndScoreInfobyScore();

    List<Map<String, Object>>    findStudentAndAvgScoreInfo();

    List<Map<String, Object>>    findStudentAndCoAndScoreInfo();

    List<Map<String, Object>>    findStudentInfoByCourseId();

    List<Map<String, Object>>    findStudentInfoByCourseId2();

    List<Map<String, Object>>    findStudentInfoByTeacherName();

    List<Map<String, Object>>    findStudentInfoNotInByTeacherName();

    List<Map<String, Object>>    findStudentInfoNotInByTeacherName2();

    List<Map<String, Object>>    findStudentInfoInByTeacherName();

    List<Map<String, Object>>    findStudentInfoInByCourseId();

    List<Map<String, Object>>    findAllAvgScoreOrderByDesc();

    List<Map<String, Object>>    findAllAvgScoreAndScore();

    List<Map<String, Object>>    findAvgScoreAndOrder();

    List<Map<String, Object>>    findScoreFirstAndSecend1();

    List<Map<String, Object>>    findScoreFirstAndSecend2();

    List<Map<String, Object>>    findScoreFirstAndSecend22();

    List<Map<String, Object>>    findCourseScoreOrder();

    List<Map<String, Object>>    findCourseScoreOrder2();

    List<Map<String, Object>>    findCourseScoreOrder3();

    List<Map<String, Object>>    findCourseScoreOrder3_2();

    List<Map<String, Object>>    findCourseScoreOrder4();















}
