package thread.demo5;

/**
 * sleep和wait方法的区别？
 * 1。sleep是Thread类的方法，wait是Object类中定义的方法
 * 2。sleep()方法可以在任何地方使用，wait()方法只能在synchronized方法或synchronized块中使用 因为我已经获取锁了，我才能再去释放锁
 * 本质区别：
 *   Thread.sleep 只会让出CPU ,不会导致锁行为的改变
 *   Object.wait不仅让出CPU,还会释放已经占有的同步资源
 *
 */
public class WaitSleepDemo {

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
                        lock.wait();//不传递参数，无限期等待 让出锁和CPU
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
                        lock.notifyAll();
                        Thread.yield();  //yield并没有使当前线程让出已经占据的锁，对锁的行为没有影响
                        Thread.sleep(2000);
                        System.out.println("thread B is done");
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
