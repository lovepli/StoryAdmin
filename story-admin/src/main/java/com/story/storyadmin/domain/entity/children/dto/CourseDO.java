package com.story.storyadmin.domain.entity.children.dto;

import com.story.storyadmin.domain.entity.children.DTeacher;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author: 59688
 * @date: 2021/7/6
 * @description: 课程对象 和DCourse相同
 */
@Data
@ToString
@NoArgsConstructor
public class CourseDO {

    protected String id; // 父类属性protected

    protected String name;

    protected DTeacher teacher;

}
