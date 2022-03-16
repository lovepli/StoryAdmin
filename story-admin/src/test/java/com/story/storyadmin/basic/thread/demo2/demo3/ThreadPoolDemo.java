package thread.demo3;

import thread.demo3.MyCallable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 通过线程池获取返回值
 * 使用线程池的好处：可以提交多个实现callable方法的类去让线程池并发的处理结果，方便我们对这些类进行统一的管理
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        //定义一个线程池
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

        //调用submit()方法
        Future<String> future = newCachedThreadPool.submit(new MyCallable());
        if(!future.isDone()){
            System.out.println("task has not finished, please wait!");
        }
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            newCachedThreadPool.shutdown(); //关闭线程池
        }
    }
}
