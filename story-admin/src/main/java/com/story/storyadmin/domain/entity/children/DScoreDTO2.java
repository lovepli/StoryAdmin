package com.story.storyadmin.domain.entity.children;


import lombok.Data;
import lombok.ToString;

import java.util.List;


/**
 * @author: lipan
 * @date: 2021/7/5
 * @description: 分数表
 */
@Data
@ToString
public class DScoreDTO2 {

        private String id;

        /**分数 **/
        private int score;

        private List<DStudent> students;




}
