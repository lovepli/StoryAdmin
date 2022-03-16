package thread.demo3;

/**
 * 问题：如何实现处理线程的返回值？
 * 1。主线程等待法 缺点：需要自己手写循环等待的代码逻辑
 * 2。使用Thread类的join()阻塞当前线程以等待子线程处理完毕
 * 3。通过Callable接口实现：通过FutureTask Or 线程池获取
 *
 */
public class CycleWait implements Runnable{

    private String value;

    public void run() {
        try {
            Thread.currentThread().sleep(5000);  //使当前线程等待5s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        value = "we have data now";
    }

    public static void main(String[] args) throws InterruptedException {
        CycleWait cw = new CycleWait();
        Thread t = new Thread(cw);
        t.start();
        //1。主线程等待法
//        while (cw.value == null){
//            Thread.currentThread().sleep(100);
//        }
        //2。使用Thread类的join()阻塞当前线程以等待子线程处理完毕
        t.join();
        System.out.println("value : " + cw.value);
    }
}
