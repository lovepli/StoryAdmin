package com.story.storyadmin.comparator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: 59688
 * @date: 2021/7/19
 * @description:
 */
@Data
@NoArgsConstructor
public class ComparePeople2 {

    public Integer age;
    public String name;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ComparePeople2(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "ComparePeople2{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
