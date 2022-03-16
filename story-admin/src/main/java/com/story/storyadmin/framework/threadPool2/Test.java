package com.story.storyadmin.framework.threadPool2;

/**
 * @author: 59688
 * @date: 2021/10/13
 * @description:
 */
public class Test {

    public static void test1(){
        BasicThreadPool threadPool = new BasicThreadPool();
        for (int i = 0; i <= 100; i++) {
            final int num = i;
            threadPool.excute(
                    () -> {
                        System.out.println(Thread.currentThread().getName() + " : i am running to deal with task of " + num);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
            );

        }
    }

    public static void test2(){
        BasicThreadPool threadPool = new BasicThreadPool();
        for (int i = 0; i <= 100; i++) {
            final int num = i;
            threadPool.excute(
                    () -> {
                        System.out.println(Thread.currentThread().getName() + " : i am running to deal with task of " + num);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
            );
            if (i == 90) {
                threadPool.shutdown();
            }
        }
    }

    public static void main(String[] args) {
          test2();
    }
}
