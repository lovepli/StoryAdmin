package com.story.storyadmin.basic.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author: lipan
 * @date: 2019-06-08
 * @description:
 */
public class Main {

 //请尽可能地使用方法引用来简化代码:
    public static void main(String[] args) throws Exception {
        String[] array = new String[] { "Orange", "apple", "blackberry", "Pear" }; ExecutorService executor = Executors.newCachedThreadPool();
// 忽略大小写异步排序:
        Future<String[]> f = executor.submit(new Callable<String[]>() {
            public String[] call() {
                String[] copy = Arrays.copyOf(array, array.length);
                Arrays.sort(copy, new Comparator<String>() {
                    public int compare(String s1, String s2) {
                        return s1.toLowerCase().compareTo(s2.toLowerCase());
                    } });
                return copy;
            }
        });
        System.out.println("Original: " + Arrays.toString(array));
        System.out.println("  Sorted: " + Arrays.toString(f.get()));
        executor.shutdown();
    }
    //改进1：
    static void change() throws Exception {
        String[] array = new String[] { "Orange", "apple", "blackberry", "Pear" };
        ExecutorService executor = Executors.newCachedThreadPool();

        // 忽略大小写异步排序:
        Future<String[]> f = executor.submit(new Callable<String[]>() {
            public String[] call() {
                String[] copy = Arrays.copyOf(array, array.length);
                //匿名内部类改为lambda表达式  -->  改为方法引用来写怎么写
                Arrays.sort(copy, (s1,s2) -> {
                    return s1.toLowerCase().compareTo(s2.toLowerCase());
                });
                return copy;
            }
        });
        System.out.println("Original: " + Arrays.toString(array));
        System.out.println("  Sorted: " + Arrays.toString(f.get()));
        executor.shutdown();

    }
    //改进2：
    static void change2() throws Exception {
        String[] array = new String[] { "Orange", "apple", "blackberry", "Pear" };
        ExecutorService executor = Executors.newCachedThreadPool();

        // 忽略大小写异步排序:
        Future<String[]> f = executor.submit(new Callable<String[]>() {
            public String[] call() {
                String[] copy = Arrays.copyOf(array, array.length);
                //lambda表达式  -->  改为方法引用来写
                 Arrays.sort(copy,String::compareToIgnoreCase);
                return copy;
            }
        });
        System.out.println("Original: " + Arrays.toString(array));
        System.out.println("  Sorted: " + Arrays.toString(f.get()));
        executor.shutdown();
    }
}
