package com.story.storyadmin.domain.vo.wind;

import lombok.Data;
import java.util.Date;
@Data
public class TableDto {

    private String title; //标题

    private String type; //类型

    private Integer level; //重要程度

    private Date createDate; // 创建日期

}
