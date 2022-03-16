package thread.demo2;
//看底层源码 https://hg.openjdk.java.net/按照视频中讲解的那样去看

/**
 * 问题1：Thread中的start和run方法的区别？
 * 1。调用start方法会创建一个新的子线程并启动
 * 2。run方法只是thread方法的一个普通方法的调用
 *
 *
 * */
public class ThreadTest {
    private static void attack() {
        System.out.println("Fight");
        System.out.println("Current Thread is : " + Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(){
            public void run(){
                attack();
            }
        };
        System.out.println("current main thread is : " + Thread.currentThread().getName());
       // t.run();
        /**
         * t.run();输出结果：
         * current main thread is : main
         * Fight
         * Current Thread is : main
         */
         t.start();
        /**
         * t.start();输出结果：
         *current main thread is : main
         * Fight
         * Current Thread is : Thread-0
         */
        // t.join();
       // t.start();
    }
}
