package com.story.storyadmin.basic.collection.demo6;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author: lipan
 * @date: 2019-06-04
 * @description:
 */
public class Customer implements Comparable { //实现Comparable自然排序的接口 ，必须实现equals(Object obj) compareTo(Object o) 和hashCode() 这三个方法

    private String name;

    private int age;

    public Customer(String name,int age) {
        this.name=name;
        this.age=age;
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
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Customer)) {
            return false;
        }
        final  Customer other = (Customer) obj;
        if (this.name.equals(other.getName()) && this.age == other.getAge()) {
            return true;
        } else {
            return false;

        }
    }

    /**
     * equals() 与compareTo(Object o)方法的比较方式应该相同
     * @param o
     * @return
     */
    @Override
    public int compareTo(Object o) {

        Customer other = (Customer) o;

        //先按name属性排序
        if (this.name.compareTo(other.getName()) > 0) {
            return 1;
        }
        if (this.name.compareTo(other.getName()) < 0) {
            return -1;
        }

        //再按age属性排序
        if (this.age > other.getAge()) {
            return 1;
        }
        if (this.age < other.getAge()) {
            return -1;
        }

        return 0;
    }

    @Override
    public int hashCode() {
        int result;
        result = (name == null ? 0 : name.hashCode());
        result=29*result+age;
        return result;
    }

    public static void main(String[] args) {
        Set<Customer> set = new TreeSet<>();
        Customer customer1 = new Customer("Tom", 16);
        Customer customer2 = new Customer("Tom", 15);
        set.add(customer1);
        set.add(customer2);

       //for循环遍历
        for (Customer c : set) {
            System.out.println(c.name+" "+c.age);  //TreeSet 使用了compareTo()进行了自然排序
            //输出：
            //Tom 15
            //Tom 16
        }
    }
}
