package thread.demo7;

/**
 * 线程安全的主演诱因：
 * 存在共享数据（也称临界资源）
 * 存在多条线程共享操作这些数据
 *
 *互斥锁的特性；
 * 互斥性；
 * 可见性；
 *
 * 对象锁和类锁的总结：
 * 1。有线程访问对象的同步代码块时，另外的线程可以访问该对象的非同步代码块
 * 2。若锁住的是同一个对象，一个线程在访问对象的同步代码块时，另外一个访问对象的同步代码块的线程会被阻塞；
 * 3。若锁住的是同一个对象，一个线程在访问对象的同步方法时，另外一个访问对象同步方法的线程会被阻塞；
 * 4。若锁住的是同一个对象，一个线程在访问对象的同步代码块使，另一个访问对象同步方法的线程会被阻塞，反之亦然。
 * 5。同一个类的不通对象的对象锁互不干扰
 * 6。类锁由于也是一种特殊的对象锁，因此表现如上述1234一致，而由于一个类只有一把对象锁，所以同一个类的不通对象使用类锁将会是同步的
 * 7。类锁和对象锁互不干扰
 */
public class SyncDemo {
    public static void main(String... args) {
        //锁的同一个对象
        SyncThread syncThread = new SyncThread();
        Thread A_thread1 = new Thread(syncThread, "A_thread1");
        Thread A_thread2 = new Thread(syncThread, "A_thread2");
        Thread B_thread1 = new Thread(syncThread, "B_thread1");
        Thread B_thread2 = new Thread(syncThread, "B_thread2");
        Thread C_thread1 = new Thread(syncThread, "C_thread1");
        Thread C_thread2 = new Thread(syncThread, "C_thread2");
        Thread D_thread1 = new Thread(syncThread, "D_thread1");
        Thread D_thread2 = new Thread(syncThread, "D_thread2");
        Thread E_thread1 = new Thread(syncThread, "E_thread1");
        Thread E_thread2 = new Thread(syncThread, "E_thread2");

        //锁的不同的对象
//        Thread A_thread1 = new Thread(new SyncThread(), "A_thread1");
//        Thread A_thread2 = new Thread(new SyncThread(), "A_thread2");
//        Thread B_thread1 = new Thread(new SyncThread(), "B_thread1");
//        Thread B_thread2 = new Thread(new SyncThread(), "B_thread2");
//        Thread C_thread1 = new Thread(new SyncThread(), "C_thread1");
//        Thread C_thread2 = new Thread(new SyncThread(), "C_thread2");
//        Thread D_thread1 = new Thread(new SyncThread(), "D_thread1");
//        Thread D_thread2 = new Thread(new SyncThread(), "D_thread2");
//        Thread E_thread1 = new Thread(new SyncThread(), "E_thread1");
//        Thread E_thread2 = new Thread(new SyncThread(), "E_thread2");

        A_thread1.start();
        A_thread2.start();
        B_thread1.start();
        B_thread2.start();
        C_thread1.start();
        C_thread2.start();
        D_thread1.start();
        D_thread2.start();
        E_thread1.start();
        E_thread2.start();
    }
}
