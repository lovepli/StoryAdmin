package com.story.storyadmin.manyThread.threadLocalDemo7.demo1;

import java.util.Random;

/**
 * @author: 59688
 * @date: 2021/7/28
 * @description: ThreadLocal实现每一个线程都有自己的专属本地变量
 */
public class ThreadLocalRunnable implements Runnable {

    ThreadLocal<StudentShareData> studenThreadLocal = new ThreadLocal<StudentShareData>();

    StudentShareData studentShareData;

    public  ThreadLocalRunnable(StudentShareData studentShareData){
        this.studentShareData =studentShareData;
    }

    @Override
    public void run() { // 执行线程方法，既线程需要做的事
        String currentThreadName = Thread.currentThread().getName();
        System.out.println(currentThreadName + " is running...");
        Random random = new Random();
        int age = random.nextInt(100); //产生一个100之内的随机整数
        System.out.println(currentThreadName + " is set age: " + age);
        StudentShareData student = getStudent(); //通过这个方法，为每个线程都独立的 new 一个 student 对象，每个线程的的 student 对象都可以设置不同的值
        student.setAge(age);
        System.out.println(currentThreadName + " is first get age: " + student.getAge());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(currentThreadName + " is second get age: " + student.getAge());

        //1、实现每个线程的student对象的age加1
         student.inc();
        // 2、实现两个线程竞争使得自己线程的student对象的age值加一
        theradInc(student.getAge());
    }


    private StudentShareData getStudent() {
        StudentShareData student = studenThreadLocal.get();
        if (null == student) {
            student = new StudentShareData();
            studenThreadLocal.set(student);
        }
        return student;
    }

    /**
     * 同步方法
     * 实现多个线程gae之和
     *
     * synchronized关键字解决的是多个线程之间访问资源的同步性，synchronized关键字可以保证被它修饰的方法或者代码块在任意时刻只能有一个线程执行。
     *      * synchronized关键字最主要的三种使用方式：
     *      *
     *      * 修饰实例方法: 作用于当前对象实例加锁，进入同步代码前要获得当前对象实例的锁
     *      * 修饰静态方法: 也就是给当前类加锁，会作用于类的所有对象实例，因为静态成员不属于任何一个实例对象，是类成员
     *                   （ static 表明这是该类的一个静态资源，不管new了多少个对象，只有一份）。所以如果一个线程A调用一个实例对象的非静态 synchronized 方法，
     *                    而线程B需要调用这个实例对象所属类的静态 synchronized 方法，是允许的，不会发生互斥现象，因为访问静态 synchronized 方法占用的锁是当前类的锁，
     *                    而访问非静态 synchronized 方法占用的锁是当前实例对象锁。
     *      * 修饰代码块: 指定加锁对象，对给定对象加锁，进入同步代码库前要获得给定对象的锁。
     *
     *      *说说 synchronized 关键字和 volatile 关键字的区别
     *      *
     *      * 多线程访问volatile关键字不会发生阻塞，而synchronized关键字可能会发生阻塞
     *      * volatile关键字能保证数据的可见性，但不能保证数据的原子性。synchronized关键字两者都能保证。
     *      * volatile关键字主要用于解决变量在多个线程之间的可见性，而 synchronized关键字解决的是多个线程之间访问资源的同步性。
     *
     */
    public synchronized void theradInc(int age) {
        age++;
        System.out.println(Thread.currentThread().getName() + ":age =" + age);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
