package thread.demo1;


//继承Thread类
public class MyThread extends Thread {
    private String name;
    public MyThread(String name){
        this.name = name;
    }
    //实现run方法
    @Override
    public void run(){
        for(int i = 0 ; i < 10 ; i ++){
            System.out.println("Thread start : " + this.name + ",i= " + i);
        }
    }
}
