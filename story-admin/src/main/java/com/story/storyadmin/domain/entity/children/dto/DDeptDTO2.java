package com.story.storyadmin.domain.entity.children.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author: lipan
 * @date: 11:56 下午
 * @description:
 */
@Data
@ToString
public class DDeptDTO2 implements Serializable {

    private static final  long serialVersionUID =1L;

    //部门id
    private String deptId;

    //部门名称
    private String deptName;

    //员工名称
    private List<String> employeeNames;

}
