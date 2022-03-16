package thread.demo1;

import thread.demo1.MyRunnable;

/**
 * 问题：thread和Runnable是什么关系？
 * 1。Thread是实现leRunnable接口的类，使得run支持多线程
 * 2。因类的单一继承原则，推荐多使用Runnable接口
 */
public class RunnableDemo {
    public static void main(String[] args) throws InterruptedException {
        MyRunnable mr1 = new MyRunnable("Runnable1");
        MyRunnable mr2 = new MyRunnable("Runnable2");
        MyRunnable mr3 = new MyRunnable("Runnable3");
        //查创建Thread对象
        Thread t1 = new Thread(mr1);
        Thread t2 = new Thread(mr2);
        Thread t3 = new Thread(mr3);
        t1.start();
        t2.start();
        t3.start();
    }
}
