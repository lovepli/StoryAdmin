package com.story.storyadmin.jsonUtil.jacksonUtil;

import lombok.Data;

import java.util.List;

/**
 * @author: lipan
 * @date: 2020/5/20
 * @description:
 */
@Data
public class AppleVO {

    private Integer id;

    private Integer parentId;

    private String name;

    private List<Apple> children;

}
