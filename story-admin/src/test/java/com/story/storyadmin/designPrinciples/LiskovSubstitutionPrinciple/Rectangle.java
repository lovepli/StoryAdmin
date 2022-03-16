package com.story.storyadmin.designPrinciples.LiskovSubstitutionPrinciple;


/**
 * @author: lipan
 * @date: 2021/8/19
 * @description:
 * 现在来描述一个经典的业务场景，用正方形、矩形和四边形的关系说明里氏替换原则，我们都知道正方形是一个特殊的长方形，那么可以创建一个长方形的父类Rectangle类，代码如下。
 */
public class Rectangle {
    private long length;
    private long width;

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public long getWidth() {
        return width;
    }

    public void setWidth(long width) {
        this.width = width;
    }
}
