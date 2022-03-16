package com.story.storyadmin.basic.dataStruct.map;

import java.util.Objects;

/**
 * @author: lipan
 * @date: 2019-06-12
 * @description:
 *
 */
public class Person {

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
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Person) {
            Person p = (Person) obj;
            return Objects.equals(this.name, p.name) && this.age == age;
        }
        return false;
    }

    @Override
    public int hashCode() {  //当我们判断两个Person对象，如果name和age字段是相等的话，就认为这两个Person对象相等；因此相同的name和相同的age必须要产生相同的hashcode值
        return Objects.hash(this.name,this.age);
    }

}
