package com.story.storyadmin.domain.entity.children.manytomany;


import com.story.storyadmin.domain.entity.children.DStudent;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author: lipan
 * @date: 2021/7/5
 * @description: 课程与学生多对多关系
 */
@Data
@ToString
public class DCourseBO implements Serializable{

    private static final  long serialVersionUID =1L;

    private String id;

    private String name;

    // 学生
    private List<DStudent> students;

}
