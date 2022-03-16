package com.story.storyadmin.basic.thread.demo;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *  守护线程
 * 守护线程是为其他线程服务的线程
 * 所有非守护线程都执行完毕后，虚拟机退出
 * 守护线程不能持有资源(如打开文件等)
 * 创建守护线程:
 * setDaemon(true)
 */
class TimerThread extends Thread {
	@Override
	public void run() {
		while (true) {
			System.out.println(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				break;
			}
		}
	}
}

public class ThreadDemo4 {

	public static void main(String[] args) throws Exception {
		System.out.println("JunitTestSuite start");
		TimerThread t = new TimerThread();
		t.setDaemon(true);  //t设置为守护线程 当main线程结束了，守护线程也就结束了
		t.start();
		Thread.sleep(5000);
		System.out.println("JunitTestSuite end");
	}
}
