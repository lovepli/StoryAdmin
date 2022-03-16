package com.story.storyadmin.manyThread.volatileDemo8.demo;

import java.util.concurrent.TimeUnit;

/**
 * @author: lipan
 * @date: 2021年08月27日 11:13 上午
 * @description: 可见性验证
 * 如下一段代码，number字段没有用volatile修饰。
 *
 * 创建一个子线程
 * 子线程sleep 3s（目的是让主线程先加载number=0的变量）
 * 子线程把number改成100。
 * 这时主线程的number仍然为0，不会同步成100
 *
 * 执行结果如下，一直卡在while循环，不会输出最后一条“执行完毕”
 *
 * 给number变量加上volatile关键字，重新执行一下，结果变了，主线程能够及时同步number值的变动，最后输出了“执行完毕”
 */
public class ThreadVisibility {
    static class MyTest {
        public  int number = 0;
        //public volatile int number = 0;
        public void changeNumber(){
            number = 100;
        }
    }
    public static void main(String[] args) throws InterruptedException{
        MyTest myTest = new MyTest();

        new Thread(() -> {
            System.out.println(String.format("线程%s开始执行", Thread.currentThread().getName()));

            try {
                //休眠3秒
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            myTest.changeNumber();
            System.out.println(String.format("线程%s的number：%d", Thread.currentThread().getName(), myTest.number));
        }, "NewThread").start();

        while (myTest.number == 0){

        }

        System.out.println("执行完毕");
    }
}
