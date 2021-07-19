package com.story.storyadmin.jsonUtil.jacksonUtil;

import lombok.Data;

import java.util.List;

/**
 * @author: lipan
 * @date: 2020/5/20
 * @description:
 */
@Data
public class AppleVO2 {

    private Integer appleId;

    private Integer parentId;

    private String appleName;

    private List<Apple> children;

}
