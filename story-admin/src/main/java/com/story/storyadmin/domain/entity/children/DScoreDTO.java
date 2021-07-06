package com.story.storyadmin.domain.entity.children;

import lombok.Data;


/**
 * @author: lipan
 * @date: 2021/7/5
 * @description: 分数表
 */
@Data
public class DScoreDTO  {

        private String id;

        /**分数 **/
        private int score;

        // 课程
        private DCourse course;

        private DStudent student;

        private DTeacher teacher;



}
