package com.story.storyadmin.设计模式.模版方法模式;

/**
 * @author: lipan
 * @date: 2021/8/22
 * @description: 模版类
 */
public abstract class AbstractTemplate {

    /**
     * 算法骨架
     */
    public Result execute() {
        //第一步：解析参数
        parseRequestParameters();
        //第二步：校验参数
        checkRequestParameters();
        //第三步：业务处理
        Object data= doBusiness();
        //第四步：组织返回参数
        return assembleResponseParameters(data);
    }

    /**
     * 解析参数
     */
    public abstract void parseRequestParameters();

    /**
     * 校验参数
     */
    public abstract void checkRequestParameters();

    /**
     * 业务处理
     */
    public abstract Object doBusiness();

    /**
     * 组织返回参数
     */
    public abstract Result assembleResponseParameters(Object object);
}
