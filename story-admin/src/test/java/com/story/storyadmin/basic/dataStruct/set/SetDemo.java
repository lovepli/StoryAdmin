package com.story.storyadmin.basic.dataStruct.set;

import java.util.*;

/**
 * @author: lipan
 * @date: 2019-06-12
 * @description:
 * Set用于存储不重复的元素集合:
 * boolean add(E e)
 * boolean remove(Object o)
 * boolean contains(Object o)
 * int size()
 *
 * Set<E> 用于存储不重复的元素集合：实际上相当于不存储value的Map
 * 放入Set的元素与作为Map的key要求相同：正确实现equals()和hashCode()
 * 利用Set可以去除重复元素
 *
 * HashSet是无序的
 * TreeSet是有序的 实现了SortedSet接口
 * 遍历按照元素的排序顺序遍历，也可以自定义排序算法
 *
 */
public class SetDemo {

    /**
     * 删除重复元素
     * @param list
     * @return
     */
    private static List<String> removeDuplicate(List<String> list) {
        //去重方法一：将含有重复元素的list集合直接放入Set的构造函数里，生成的Set集合就是自动去重的集合
       Set<String> set = new HashSet<>(list);//创建HashSet 传入List<String> 他会自动去掉重复的元素生成一个新的无重复元素的集合
        //查看源码，底层构造hashSet对象t其实是创建了一个hashMap对象，set就是HashMap的key,然后交给map来处理删除重复元素的实现

        //方法二：
        // Set<String> set = new TreeSet<>(list); //实现排序并且去重
        //查看源码，底层构造TreeSet对象t其实是创建了一个TreeMap对象，set就是TreeMap的key,然后交给map来处理删除重复元素并且排序的实现

        //方法三：自定义排序 匿名内部类的使用
//        Set<String> set = new TreeSet<>(new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return -o1.compareTo(o2);
//            }
//        });
//        set.addAll(list);
         return new ArrayList<String>(set);//将set集合转换为ArrayList集合
    }

    public static void main(String[] args) {
        List<String> list1 = Arrays.asList("pear", "apple", "banana", "orange","apple", "banana");  //包含很多重复元素的list集合
        List<String> list2 = removeDuplicate(list1);
        System.out.println(list2);
    }


}
