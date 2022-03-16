package com.story.storyadmin.basic.thread.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: lipan
 * @date: 2019-06-10
 * @description:  练习：
 * join练习 小明设计了一个多线程程序，希望创建3个线程，每个线程先打印
 * Hello, xxx!
 * 然后等待1秒，再打印: Goodbye, xxx!
 *
 * 这里我没有成功！！！！ß
 */
class HellThread extends Thread{

    private String name;

    public HellThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Hello, " + name + "!");
    }

    public String getname() {
        return name;
    }

}

public class ThreadDemo3 {
    public static void main(String[] args) throws Exception {
        List<Thread> threads = new ArrayList<>();
        for (String name : Arrays.asList("Bob", "Alice", "Tom")) {
            threads.add(new HellThread(name));

        }

        System.out.println("start");
        for (Thread t : threads) {
            t.start();
            t.join();
            Thread.sleep(2000);
        }
        try {
            Thread.sleep(1000);
            System.out.println("(等待约1秒)");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        for (int i = 0; i < threads.size(); i++) {
//           String s=threads.get(i).getName();
//            System.out.println(s);
//        }

        System.out.println("Goodbye, " +"Bob"+ "!");
        System.out.println("Goodbye, " +"Alice"+ "!");
        System.out.println("Goodbye, " +"Tom"+ "!");
        System.out.println("end");

    }


//    小明期望的输出是:
//    START
//    Hello, Bob!
//    Hello, Alice!
//    Hello, Tom!
//    (等待约1秒)
//    Goodbye, Bob!
//    Goodbye, Alice!
//    Goodbye, Tom!
//    END

}
