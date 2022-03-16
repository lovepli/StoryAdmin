package com.story.storyadmin.basic.lambda;

import java.util.Arrays;
import java.util.Comparator;  //比较器

/**
 * Lambda表达式的使用
 * 补充扩展：Java中只定义了一个方法的接口都有哪些？3个。 Callable Comparator Runnable
 */
public class LambdaSample {

    //练习一：将Comparator接口实现类改为lambda表达式
    private static void ComparatorTest() {
        String[] words = "Improving code with Lambda expressions in Java".split(" ");

//		Arrays.sort(words, new Comparator<String>() {
//			@Override
//			public int compare(String s1, String s2) {
//				// 忽略大小写排序:
//				return s1.toLowerCase().compareTo(s2.toLowerCase());
//			}
//		});

        //匿名内部类改写为Lambda表达式的写法
        Arrays.sort(words, (s1,s2) ->{
            // 忽略大小写排序:
            return s1.toLowerCase().compareTo(s2.toLowerCase());

        });
        System.out.println(Arrays.toString(words));
    }

    //练二：将Runnable接口实现类改为lambda表达式
    private static void RunnableTest() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("start new thread...");
            } });
        t.start();
    }

	public static void main(String[] args) throws Exception {

        ComparatorTest();
	}


}
