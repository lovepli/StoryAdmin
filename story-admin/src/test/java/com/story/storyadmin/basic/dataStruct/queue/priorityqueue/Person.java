package com.story.storyadmin.basic.dataStruct.queue.priorityqueue;

/**
 * @author: lipan
 * @date: 2019-06-12
 * @description:
 *
 */
public class Person implements Comparable<Person> {

     private String name;
     private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "(Person: " + name + ", " + age + ")";
    }


    @Override
    public int compareTo(Person o) {   //这里我们按照name字段排序 即名字的字典顺序
        return this.name.compareTo(o.name);
    }
}
