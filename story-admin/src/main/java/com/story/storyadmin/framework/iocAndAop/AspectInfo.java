package com.story.storyadmin.framework.iocAndAop;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: 59688
 * @date: 2021/9/26
 * @description: 切面信息包装类（增强的动作/增强对象地址/横切顺序）
 */
@AllArgsConstructor
@Getter
public class AspectInfo {
    private int orderIndex;
    private DefaultAspect aspectObject;
    private PointcutLocator pointcutLocator;
}