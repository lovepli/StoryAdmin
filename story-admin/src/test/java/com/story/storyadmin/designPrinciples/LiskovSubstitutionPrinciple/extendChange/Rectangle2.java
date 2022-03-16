package com.story.storyadmin.designPrinciples.LiskovSubstitutionPrinciple.extendChange;

/**
 * @author: lipan
 * @date: 2021/8/19
 * @description:
 */
public class Rectangle2 implements QuardRangle{
    private long length;
    private long width;

    @Override
    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }
    @Override
    public long getWidth() {
        return width;
    }

    public void setWidth(long width) {
        this.width = width;
    }
}
