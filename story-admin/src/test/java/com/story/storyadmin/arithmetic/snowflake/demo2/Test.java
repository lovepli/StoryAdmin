package com.story.storyadmin.arithmetic.snowflake.demo2;

/**
 * @author: 59688
 * @date: 2021/10/12
 * @description:
 */
public class Test {

    public static void main(String[] args) {
        Thread threadA  = new Thread(() ->{
            SnowFlake snowFlakeA = SnowFlake.getInstance();
            System.out.println("线程A获取的实例的内存地址是："+snowFlakeA.toString());
            System.out.println("生成的id"+snowFlakeA.getNum());
        });
        Thread threadB = new Thread(() ->{
            SnowFlake snowFlakeB =SnowFlake.getInstance();
            System.out.println("线程B获取的实例的内存地址是："+snowFlakeB.toString());
            System.out.println("生成的id"+snowFlakeB.getNum());
        });

        threadA.start();
        threadB.start();
    }
}
