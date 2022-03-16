package com.story.storyadmin.designPatterns.payService9.demo3;

/**
 * @author: lipan
 * @date: 2021/8/20
 * @description:  规则接口
 */
public interface Rule {
    boolean execute(RuleDTO dto);
}
