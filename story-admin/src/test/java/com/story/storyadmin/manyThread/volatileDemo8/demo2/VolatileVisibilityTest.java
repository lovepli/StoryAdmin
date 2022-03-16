package com.story.storyadmin.manyThread.volatileDemo8.demo2;

/**
 * @author: lipan
 * @date: 2021年10月05日 2:00 下午
 * @description: 可见性验证
 */
public class VolatileVisibilityTest {
    //private static volatile boolean initFlag = false;
    private static boolean initFlag = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            System.out.println("waiting data");
            while (!initFlag) {
                //System.out.println();
                //里面添加锁共享变量就不会缓存
                synchronized (VolatileVisibilityTest.class) {

                }
            }
            System.out.println("========success!!!");
        }).start();


        Thread.sleep(2000);

        new Thread(() -> prepareData()).start();


    }

    private static void prepareData() {
        System.out.println("开始修改initFlag...");
        initFlag = true;
        System.out.println("initFlag修改成功...");
    }
}
