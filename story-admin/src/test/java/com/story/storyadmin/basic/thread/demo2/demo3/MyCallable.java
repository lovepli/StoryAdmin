package thread.demo3;

import java.util.concurrent.Callable;

//实现Callable接口
public class MyCallable implements Callable<String> {

    //重写方法
    @Override
    public String call() throws Exception{
        String value="test";
        System.out.println("Ready to work");
        Thread.currentThread().sleep(5000); //等待5s
        System.out.println("task done");
        return  value;
    }

}
