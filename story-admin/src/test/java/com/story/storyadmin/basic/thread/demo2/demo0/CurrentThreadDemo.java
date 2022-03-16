package thread.demo0;
//CurrentThreadDemo.java 是一个进程
public class CurrentThreadDemo {
    public static void main(String[] args) {
        System.out.println("Current Thread: " + Thread.currentThread().getName()); //获取到当前的线程的名字
    }
}
