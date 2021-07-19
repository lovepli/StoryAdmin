package com.story.storyadmin.comparator;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author: 59688
 * @date: 2021/7/19
 * @description: 对象比较排序 https://blog.csdn.net/weixin_43610698/article/details/93409077
 *      * Compareable和Comparator两者比较总结：
 *      * Comparable是排序接口；若一个类实现了Comparable接口，就意味着“该类支持排序”；而Comparator是比较器；我们若需要控制某个类的次序，可以建立一个“该类的比较器”来进行排序。
 *      * Comparable相当于“内部比较器”，而Comparator相当于“外部比较器”。
 *      * Comparable是通用的接口，用户可以实现它来完成自己特定的比较，而Comparator可以看成是一种算法的实现，在需要容器集合实现比较功能的时候，来指定这个比较器，这可以看成是一种设计模式，将算法和数据分离。
 *      * 前者应该比较固定，和一个具体类相绑定，而后者比较灵活，它可以被用于各个需要比较功能的类使用
 */
public class ComparePeople implements Comparable<ComparePeople> {
    public Integer age;
    public String name;

        public ComparePeople() {
            super();
            // TODO Auto-generated constructor stub
        }
        public ComparePeople(String name, int age) {
            super();
            this.name = name;
            this.age = age;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public Integer getAge() {
            return age;
        }
        public void setAge(Integer age) {
            this.age = age;
        }
        @Override
        public String toString() {
            return name + "_____" + age;
        }
        //重写equals方法
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof ComparePeople) {
                ComparePeople anotherPeople = (ComparePeople) obj;

                if (this.name.equals(anotherPeople.name)) {
                    return true;
                }
            }
            return false;
        }
        //重写hashCode方法
        @Override
        public int hashCode() {

            return this.name.hashCode();
        }
        //重写compareTo方法
        @Override
        public int compareTo(ComparePeople people) {
            if (this.age == people.age) {
                // 调用String中重写的compareTo方法来比较name的大小
                return this.name.compareTo(people.name);
            }
            return this.age - people.age;
        }

    public static void main(String[] args) {
        //创建一个集合
        ArrayList<ComparePeople> arrayList = new ArrayList<>();
        arrayList.add(new ComparePeople("哼哼", 22));
        arrayList.add(new ComparePeople("小哈", 22));
        arrayList.add(new ComparePeople("哼哼的博客", 25));
        arrayList.add(new ComparePeople("哼哼", 23));
        arrayList.add(new ComparePeople("奋斗的哼哼", 30));
        System.out.println("排序之前********************");
        for (ComparePeople people : arrayList) {
            System.out.println(people);
        }
        //调用排序的方法
        Collections.sort(arrayList);
        System.out.println("排序之后*******************");
        for (ComparePeople people : arrayList) {
            System.out.println(people);
        }
    }



}

