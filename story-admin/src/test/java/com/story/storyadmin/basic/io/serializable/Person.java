package com.story.storyadmin.basic.io.serializable;

import java.io.Serializable;


/**
 * @author: lipan
 * @date: 2019-06-09
 * @description:
 */
public class Person implements Serializable {


    private final String name;

    public Person(String name) {
        System.out.println("Creat "+name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "(Preson: "+name +")";
    }


}
