package com.story.storyadmin.basic.collection.hashmap.demo2;

/**
 * @author: lipan
 * @date: 2021年09月01日 2:55 下午
 * @description:
 */
class Student2 implements Comparable<Student2> {

    private Integer id;
    private String name;

    Student2(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public String toString() {
        return "[id="+id + ", name=" + name + "]";
    }

    @Override
    public int compareTo(Student2 o) {
        return this.id.compareTo(o.id);
    }
}
