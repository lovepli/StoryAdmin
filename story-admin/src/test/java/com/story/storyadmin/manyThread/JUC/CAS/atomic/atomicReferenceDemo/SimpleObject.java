package com.story.storyadmin.manyThread.JUC.CAS.atomic.atomicReferenceDemo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: lipan
 * @date: 2021年08月27日 10:31 上午
 * @description:
 */
@Data
@AllArgsConstructor
public class SimpleObject {
    private String name;
    private Integer age;

    @Override
    public String toString() {
        return "SimpleObject{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}


