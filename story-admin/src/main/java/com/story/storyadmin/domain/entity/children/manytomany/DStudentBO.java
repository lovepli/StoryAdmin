package com.story.storyadmin.domain.entity.children.manytomany;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.story.storyadmin.domain.entity.children.DCourse;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author: lipan
 * @date: 2021/7/5
 * @description: 学生与课程多对多关系
 */
@Data
@ToString
public class DStudentBO implements Serializable {

    private static final  long serialVersionUID =1L;

    private String id;

    private String name;

    @JsonFormat(pattern ="yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date birthday;

    private char sex;

    // 课程
    private List<DCourse> courses;


}
