package com.story.storyadmin.basic.dataStruct.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: lipan
 * @date: 2019-06-12
 * @description:  集合和数组之间的转换
 */
public class listDemo {

    public static void listToArray() {
        List<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Pear");
        list.add("Orange");
        System.out.println("======打印集合=====");
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println("======打印数组=====");
       // System.out.println("集合元素的个数："+list.size());
        String[] str = list.toArray(new String[list.size()]);
        for (String ss : str) {
            System.out.println(ss);
        }
    }

    public static void arrayToList1() {
        //把Array变为List<E>
        //方法一： <T> List<T> Arrays.asList(T..a)
        Integer[] array = {1, 2, 3};
        List<Integer> list1 = Arrays.asList(array);
        //注意返回的list不是ArrayList 返回的list1 是Arrays内部的list，不是ArrayList,list1只是可读的！！！ add一个新的元素会出现错误，报异常Exception in thread "main" java.lang.UnsupportedOperationException
        System.out.println("======打印集合list1=====");
        for (Integer i : list1) {
            System.out.println(i);
        }
        System.out.println("向集合中添加新的元素======");
        try {
            list1.add(4);    //list1集合中不可以添加新的元素 报异常 java.lang.UnsupportedOperationException
        } catch (Exception e) {
            System.out.println(e);
        }finally {
            for (Integer i : list1) {
                System.out.println(i);
            }
        }
    }

    public static void arrayToList2() {

        //把Array变为List<E>
        //方法一： <T> List<T> Arrays.asList(T..a)
        Integer[] array = {1, 2, 3};
        List<Integer> list1 = Arrays.asList(array);


        //方式二：
        List<Integer> arrayList = new ArrayList<>();
        arrayList.addAll(list1);
        System.out.println("======打印集合arrayList=====");
        for (Integer i : arrayList) {
            System.out.println(i);
        }
        System.out.println("向集合中添加新的元素======");
        arrayList.add(4);  //可以添加新的元素
        for (Integer i : arrayList) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {

       // listToArray();

      //  arrayToList1();
        arrayToList2();

    }
}
