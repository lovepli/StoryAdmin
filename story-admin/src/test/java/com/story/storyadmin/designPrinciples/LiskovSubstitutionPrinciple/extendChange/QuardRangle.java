package com.story.storyadmin.designPrinciples.LiskovSubstitutionPrinciple.extendChange;

/**
 * @author: lipan
 * @date: 2021/8/19
 * @description:
 * 创建一个基于长方形与正方形共同的抽象——四边形QuardRangle接口
 */
public interface QuardRangle {

    long getLength();

    long getWidth();
}
