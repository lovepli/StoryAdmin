package com.story.storyadmin.manyThread.JUC.CAS.atomic.atomicIntegerDemo16;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author: lipan
 * @date: 12:06 上午
 * @description: 2. 数组类型的使用
 */
public class AtomicIntegerArrayTest {
    private final static AtomicIntegerArray ATOMIC_INTEGER_ARRAY = new AtomicIntegerArray(new int[]{1,2,3,4,5,6,7,8,9,10});
    public static void main(String []args) throws InterruptedException {
        Thread []threads = new Thread[10];
        for(int i = 0 ; i < 10 ; i++) {
            final int index = i;
            final int threadNum = i;
            threads[i] = new Thread() {
                public void run() {
                    int result = ATOMIC_INTEGER_ARRAY.addAndGet(index, index + 1);
                    System.out.println("线程编号为：" + threadNum + " , 对应的原始值为：" + (index + 1) + "，增加后的结果为：" + result);
                }
            };
            threads[i].start();
        }
        for(Thread thread : threads) {
            thread.join();
        }
        System.out.println("=========================>\n执行已经完成，结果列表：");
        for(int i = 0 ; i < ATOMIC_INTEGER_ARRAY.length() ; i++) {
            System.out.println(ATOMIC_INTEGER_ARRAY.get(i));
        }
    }
}
