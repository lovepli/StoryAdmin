package thread.demo4;

/**
 * @author: lipan
 * @date: 2019-06-04
 * @description:
 * 问题：notify和motifyAll的区别：
 * 两个概念：
 * 锁池EntryList
 * 等待池WaitSst
 * 1。notify：会让所有处于等待池的线程全部进入到锁池去竞争获取锁的机会
 * 2。notifyAll:只会随机选取一个处于等待池中的线程进入锁池去竞争获取锁的机会
 */
public class WaitSleepDemo2 {

    public static void main(String[] args) {

        final Object lock = new Object();  //锁对象
        //第一个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread A is waiting to get lock");
                synchronized (lock){
                    try {
                        System.out.println("thread A get lock");
                        Thread.sleep(20); //A 睡眠20毫秒 sleep只让出cpu,不让出锁
                        System.out.println("thread A do wait method");
                        lock.wait();//不传递参数，无限期等待 只有唤醒它，才能继续执行下一步
                        System.out.println("thread A is done");
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        try{
            Thread.sleep(10);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        //第二个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread B is waiting to get lock");
                synchronized (lock){
                    try {
                        System.out.println("thread B get lock");
                        System.out.println("thread B is sleeping 10 ms");
                        Thread.sleep(10);
                        lock.notify();  //唤醒无限等待的A线程继续执行
                        // lock.notifyAll();  //或者使用lock.notifyAll() 也是一样的
                        // Thread.yield();
                        System.out.println("thread B is done");
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
