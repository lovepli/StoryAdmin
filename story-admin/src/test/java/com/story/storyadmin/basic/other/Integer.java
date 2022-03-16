package com.story.storyadmin.basic.other;

/**
 * @author: lipan
 * @date: 2019-06-07
 * @description: 简单的包装类Integer
 * 定义一个Integer类，包含一个实例字段int
 */
public class Integer {

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Integer(int value) {
        this.value = value;
    }

    public static void main(String[] args) {
        Integer n = null;
        Integer n2 = new Integer(99);
    }

}
