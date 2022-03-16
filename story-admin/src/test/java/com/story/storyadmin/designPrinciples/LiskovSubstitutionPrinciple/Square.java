package com.story.storyadmin.designPrinciples.LiskovSubstitutionPrinciple;


/**
 * @author: lipan
 * @date: 2021/8/19
 * @description:
 * 创建正方形Square类继承长方形
 */

public class Square extends Rectangle {

    private long sideLength;

    @Override
    public long getLength() {
        return sideLength;
    }

    @Override
    public void setLength(long length) {
        this.sideLength = length;
    }

    @Override
    public long getWidth() {
        return sideLength;
    }

    @Override
    public void setWidth(long width) {
        this.sideLength = width;
    }
}

