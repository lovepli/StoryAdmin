package thread.demo1;

import thread.demo1.MyThread;

/**
 * 问题：线程的六个状态；
 * 1.新建(New):创建后尚未启动的线程的状态
 * 2。运行(Runnable)：包含Running和Ready
 * 3.无限期等待(Waiting)：不会被分配CPU执行时间，需要显示被唤醒 3种方式：
 *   没有设置TimeOut参数的Object.wait()方法
 *   没有设置TimeOut参数的Thread.join()方法
 *   LockSupport.park()方法
 * 4。限期等待(Time Waiting)：在一定时间后会有系统自动唤醒 5种方式：
 *   Thread.sleep()方法
 *   设置了TimeOut参数的Object.wait()方法
 *   设置了TimeOut参数的Thread.join()方法
 *   LockSupport.parkNanos()方法
 *   LockSupport.parkUntil()方法
 * 5。阻塞(Blocked):等待获取排它锁
 * 6。结束(Terminatedted):以终止线程的状态，线程已经结束执行 （当线程的run方法完成时或者main方法执行完）
 */
public class ThreadDemo {
    public static void main(String[] args) {
        MyThread mt1 = new MyThread("Thread1");
        MyThread mt2 = new MyThread("Thread2");
        MyThread mt3 = new MyThread("Thread3");
        mt1.start();
        mt2.start();
        mt3.start();
    }
}
