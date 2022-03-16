package com.story.storyadmin.manyThread.JUC.CAS.atomic.atomicReferenceDemo;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: lipan
 * @date: 2021年08月27日 12:46 下午
 * @description: java并发包(1)-AtomicReference和AtomicStampedReference https://my.oschina.net/u/1262062/blog/886151
 *
 * 打一个比方，如果有一家蛋糕店，为了挽留客户，绝对为贵宾卡里余额小于20元的客户一次性赠送20元，刺激消费者充值和消费。但条件是，每一位客户只能被赠送一次。
 *
 * 现在，我们就来模拟这个场景，为了演示AtomicReference，我在这里使用AtomicReference实现这个功能。
 * 首先判断用户余额并给予赠予金额。如果已经被其他用户处理，那么当前线程就会失败。因此，可以确保用户只会被充值一次。
 * 此时，如果很不幸的，用户正好正在进行消费，就在赠予金额到账的同时，他进行了一次消费，使得总金额又小于20元，并且正好累计消费了20元。使得消费、赠予后的金额等于消费前、赠予前的金额。这时，后台的赠予进程就会误以为这个账户还没有赠予，所以，存在被多次赠予的可能。万幸的是jdk给我提供了一个类AtomicStampedReference
 */
public class AtomicReferenceDemo {
    // 设置账户初始值小于20，显然这是一个需要被充值的账户
    static AtomicReference<Integer> money = new AtomicReference<Integer>(19);

    public static void main(String args[]) {
        //模拟多个线程同时更新后台数据库，为用户充值
        for (int i = 0; i < 3; i++) {
            new Thread() {
                public void run() {
                    while (true) {
                        while (true) {
                            Integer m = money.get();
                            if (m < 20) {
                                if (money.compareAndSet(m, m + 20)) {
                                    System.out.println("余额小于20元，充值成功，余额:" + money.get() + "元");
                                    break;
                                }
                            } else {
                                //System.out.println("余额大于20元，无需充值");
                                break;
                            }
                        }
                    }
                }
            }.start();
        }

        //有一个线程一直在消费
        new Thread() {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    while (true) {
                        Integer m = money.get();
                        if (m > 10) {
                            System.out.println("大于10元");
                            if (money.compareAndSet(m, m - 10)) {
                                System.out.println("成功消费10元，余额:" + money.get());
                                break;
                            }
                        } else {
                            System.out.println("没有足够的金额");
                            break;
                        }
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }.start();
    }
}

