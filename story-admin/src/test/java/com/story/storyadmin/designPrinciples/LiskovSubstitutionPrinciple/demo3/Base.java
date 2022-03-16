package com.story.storyadmin.designPrinciples.LiskovSubstitutionPrinciple.demo3;

import java.util.HashMap;

/**
 * @author: lipan
 * @date: 2021/8/19
 * @description:
 */
public class Base {
    public void method(HashMap map){
        System.out.println("父类被执行");
    }
}

