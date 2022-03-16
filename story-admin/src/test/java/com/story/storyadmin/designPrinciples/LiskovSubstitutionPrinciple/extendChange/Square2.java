package com.story.storyadmin.designPrinciples.LiskovSubstitutionPrinciple.extendChange;

/**
 * @author: lipan
 * @date: 2021/8/19
 * @description:
 */
public class Square2 implements QuardRangle{
    private long sideLength;

    @Override
    public long getLength() {
        return sideLength;
    }

    public void setLength(long length) {
        this.sideLength = length;
    }

    @Override
    public long getWidth() {
        return sideLength;
    }

    public void setWidth(long width) {
        this.sideLength = width;
    }

}
