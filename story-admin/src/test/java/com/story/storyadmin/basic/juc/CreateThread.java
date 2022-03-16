package com.story.storyadmin.basic.juc;

/**
 * @author: lipan
 * @date: 2019-06-27
 * @description:
 */

class CreateThread2 implements Runnable{

    @Override
    public void run() {
        System.out.println("创建线程的方式一：实现runnable接口");
    }

    //Thread(Runnable target)在创建线程对象是向构造方法的参数传递一个实现Runnable接口类的实例
    //该实例对象称为所创建线程的目标对象，当线程调用start()方法时，
    //一旦轮到他来享用CPU资源，目标对象就会自动调用run()方法(接口回调),这一过程是自动实现的，
    public static void main(String[] args) {

        Thread t1=new Thread(new CreateThread2());
        t1.start();
        new Thread(new CreateThread2()).start();  //第二个线程

    }

}

class CreateThread1 extends Thread {

    @Override
    public void run() {
        System.out.println("创建线程的方式一：继承thread类");
    }

    public static void main(String[] args) {

        CreateThread1 t1 = new CreateThread1();
        t1.start();
        new CreateThread1().start(); //第二个线程

    }
}


 public class CreateThread {


    public static void main(String[] args) {
        //Thread(Runnable target,String name)
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("创建线程的方法三");
                System.out.println("创建线程的方法一：匿名内部类");
            }
        },"your thread name").start();

        //创建线程的方法二：lambda表达式
        new Thread(() ->{
           // System.out.println(  System.out.println("创建线程的方法二：lambda表达式");
        },"your name").start();
    }

}








