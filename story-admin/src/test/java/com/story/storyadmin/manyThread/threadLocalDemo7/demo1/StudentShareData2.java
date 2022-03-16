package com.story.storyadmin.manyThread.threadLocalDemo7.demo1;

import lombok.Data;
import lombok.ToString;

/**
 * @author: 59688
 * @date: 2021/7/28
 * @description: 单例模式
 */
@Data
@ToString
public class StudentShareData2 {

    private int age;

    private volatile static StudentShareData2 uniqueInstance;

    private StudentShareData2() {
    }

    public static StudentShareData2 getUniqueInstance() {
        //先判断对象是否已经实例过，没有实例化过才进入加锁代码
        if (uniqueInstance == null) {
            //类对象加锁
            synchronized (StudentShareData2.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new StudentShareData2();
                }
            }
        }
        return uniqueInstance;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }





    /**
     *
     * 对当前线程对象的年龄加1
     * student已经是每个线程都有单独的一个student对象了，不需要使用同步方法
     */
    public  void inc() {
        age++;
        System.out.println(Thread.currentThread().getName() + ": invoke inc method num =" + age);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

/**
 * 3.1. ThreadLocal简介
 * 通常情况下，我们创建的变量是可以被任何一个线程访问并修改的。如果想实现每一个线程都有自己的专属本地变量该如何解决呢？
 * JDK中提供的ThreadLocal类正是为了解决这样的问题。 ThreadLocal类主要解决的就是让每个线程绑定自己的值，
 * 可以将ThreadLocal类形象的比喻成存放数据的盒子，盒子中可以存储每个线程的私有数据。
 *
 * 如果你创建了一个ThreadLocal变量，那么访问这个变量的每个线程都会有这个变量的本地副本，这也是ThreadLocal变量名的由来。
 * 他们可以使用 get（） 和 set（） 方法来获取默认值或将其值更改为当前线程所存的副本的值，从而避免了线程安全问题。
 *
 * 再举个简单的例子：
 * 比如有两个人去宝屋收集宝物，这两个共用一个袋子的话肯定会产生争执，但是给他们两个人每个人分配一个袋子的话就不会出现这样的问题。
 * 如果把这两个人比作线程的话，那么ThreadLocal就是用来避免这两个线程竞争的。
 */
