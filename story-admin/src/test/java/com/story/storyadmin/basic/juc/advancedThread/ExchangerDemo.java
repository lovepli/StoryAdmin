package com.story.storyadmin.basic.juc.advancedThread;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author: lipan
 * @date: 2019-06-05
 * @description:
 */
public class ExchangerDemo {

    private static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        //代表男生和女生
        ExecutorService service = Executors.newFixedThreadPool(2);

        service.execute(() ->{
            try{
                //男生对女生说的话
                String girl=exchanger.exchange("我其实暗恋你很久了。。。");
                System.out.println("女生说："+girl);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        });

        service.execute(() ->{
            try{
                System.out.println("女生慢慢的从教室走出来。。。");
                TimeUnit.SECONDS.sleep(3);

                //女生对男生说的话
                String boy=exchanger.exchange("我很喜欢你。。。");
                System.out.println("男生说："+boy);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        });

    }
}
