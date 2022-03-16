package com.story.storyadmin.designPatterns.payService9.demo3;

/**
 * @author: lipan
 * @date: 2021/8/20
 * @description:  基础规则
 */
public abstract class BaseRule implements Rule{

    public static final String MATCH_ADDRESS_START= "北京";

    public static final String MATCH_NATIONALITY_START= "中国";

    protected <T> T convert(RuleDTO dto) {
        return (T) dto;
    }

    @Override
    public boolean execute(RuleDTO dto) {
        return executeRule(convert(dto));
    }

    protected <T> boolean executeRule(T t) {
        return true;
    }

}