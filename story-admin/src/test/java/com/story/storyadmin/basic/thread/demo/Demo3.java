package com.story.storyadmin.basic.thread.demo;

/**
 *
 死锁产生的条件:
 多线程各自持有不同的锁，并互相试图获取对方已持有的锁，双方无限等待下去:导致死锁
 如何避免死锁:
 多线程获取锁的顺序要一致
 */
class SharedObject {

	final Object lockA = new Object();
	final Object lockB = new Object();

	int accountA = 1000;
	int accountB = 2000;

	public void a2b(int balance) {
		synchronized (lockA) {  //锁的顺序不一致，会造成死锁
			accountA -= balance;
			synchronized (lockB) {
				accountB += balance;
			}
		}
	}

	public void b2a(int balance) {
		synchronized (lockB) {
			accountB -= balance;
			synchronized (lockA) {
				accountA += balance;
			}
		}
	}
}

class AThread extends Thread {
	public void run() {
		for (int i = 0; i < Demo3.LOOP; i++) {
            Demo3.shared.a2b(1);
			if (i % 100 == 0) {
				System.out.println(".");
			}
		}
	}
}

class BThread extends Thread {
	public void run() {
		for (int i = 0; i < Demo3.LOOP; i++) {
            Demo3.shared.b2a(1);
			if (i % 100 == 0) {
				System.out.println(".");
			}
		}
	}
}

public class Demo3 {

	final static int LOOP = 1000;

	public static SharedObject shared = new SharedObject();

	public static void main(String[] args) throws Exception {
		Thread t1 = new AThread();
		Thread t2 = new BThread();
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println("END");
	}
}
