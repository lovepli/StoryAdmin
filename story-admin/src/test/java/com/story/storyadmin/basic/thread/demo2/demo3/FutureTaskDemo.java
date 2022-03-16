package thread.demo3;

import thread.demo3.MyCallable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //新建一个FutureTask对象
        FutureTask<String> task = new FutureTask<String>(new MyCallable());
        new Thread(task).start();

        if(!task.isDone()){
            System.out.println("task has not finished, please wait!");
        }
        System.out.println("task return: " + task.get());  //task.get()是等待MyCallable的call(）方法有返回值他才去执行的

    }
}
