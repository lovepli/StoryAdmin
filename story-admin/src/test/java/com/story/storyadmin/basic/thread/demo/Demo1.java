package com.story.storyadmin.basic.thread.demo;

/**
 * 线程同步
 * 多线程同时修改变量，会造成逻辑错误
 * 需要通过synchronized同步
 * 同步的本质就是给指定对象加锁
 * 注意加锁对象必须是同一个实例
 * 对JVM定义的单个原子操作不需要同步
 */
class AddThread extends Thread {
	public void run() {
		for (int i = 0; i < Demo1.LOOP; i++) {
            synchronized (Demo1.Lock) {  //加锁
                Demo1.count += 1;
            }
		}
	}
}

class DecThread extends Thread {
	public void run() {
		for (int i = 0; i < Demo1.LOOP; i++) {
            synchronized (Demo1.Lock) {
                Demo1.count -= 1;
            }
		}
	}
}

public class Demo1 {

	final static int LOOP = 10000;  //没有加锁，多次执行，结果有的不是0  多线程同时修改变量，会造成逻辑错误

	public static int count = 0;

    public static final Object Lock = new Object();  //定以一个锁对象

	public static void main(String[] args) throws Exception {
		Thread t1 = new AddThread();
		Thread t2 = new DecThread();
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(count);
	}
}
