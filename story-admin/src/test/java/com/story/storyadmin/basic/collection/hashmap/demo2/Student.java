package com.story.storyadmin.basic.collection.hashmap.demo2;

import lombok.Data;

/**
 * @author: lipan
 * @date: 2021年09月01日 2:55 下午
 * @description:
 */
@Data
public class Student {

    private Integer id;
    private String name;

    Student(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public String toString() {
        return "[id="+id + ", name=" + name + "]";
    }

}
