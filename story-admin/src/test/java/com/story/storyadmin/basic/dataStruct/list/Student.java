package com.story.storyadmin.basic.dataStruct.list;

import java.util.Objects;

/**
 * @author: lipan
 * @date: 2019-06-12
 * @description:
 *
 * 编写equals方法 List
 * 判断元素是否存在或者查找元素索引:
 * boolean contains(Object o) 是否包含某个元素
 * int indexOf(Object o) 查找某个元素的索引，不存在返回-1
 * 要正确调用contains / indexOf方法，放入的实例要正确实现equals()
 *
 * equals()编写方法:
 * 1. 判断this==o
 * 2. 判断o instanceof Person
 * 3. 强制转型，并比较每个对应的字段
 * 基本类型字段用==直接比较 引用类型字段借助Objects.equals()判断
 */
public class Student {

     private String name;
     private int age;

    public Student(String name, int age) {
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

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "(Student: " + name + ", " + age + ")";
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        //首先判断传入的对象是不是当前对象o
        if (this == o) {
            return true;
        }
        //紧接着判断传入的对象o是不是Student类型
        if (o instanceof Student) {
            //如果是Student类型则强制转换为Student类型
            Student s = (Student) o;
            //比较当前对象s他的字段是不是完全相等，这里需要注意的是对于String name字段使用equals来比较，对于int age字段使用 ==比较
           // return s.name.equals(this.name) && s.age==this.age;  //这里name可能为null，则会抛出空指针异常
            //return (s.name == this.name || s.name !=null && s.name.equals(this.name)) && s.age==this.age; //对这段代码进行优化
            return (Objects.equals(s.name, this.name)) && s.age==this.age; //引用类型字段借助Objects.equals()判断
        }
        return false;//如果不是Student类型直接返回false
    }

}
