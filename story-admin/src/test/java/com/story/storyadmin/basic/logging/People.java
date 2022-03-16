package com.story.storyadmin.basic.logging;

/**
 * @author: lipan
 * @date: 2019-06-08
 * @description:
 */
public class People {

    private String name;


    public People(String name) {
        this.name = name;
    }

     public String hello() {
        return "Hello! "+this.name;
    }

    public void test() {
        int i = 100 / 0;
        System.out.println(i);
    }


}
