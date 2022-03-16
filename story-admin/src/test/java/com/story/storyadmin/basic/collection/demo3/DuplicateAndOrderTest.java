package com.story.storyadmin.basic.collection.demo3;

import java.util.*;

/**
 * @author: lipan
 * @date: 2019-06-04
 * @description:
 */
public class DuplicateAndOrderTest {

    public static void main(String[] args) {

        //List 特点：1.有序（存储和取出的顺序）2.可重复 3.可通过索引值取出元素
        /**
         * LinkedList底层为链表，查询慢，增删快(链表的增删不涉及到元素的移动？？？)。线程不安全，效率高
         * 线程不安全：方法里面既没有用到锁，也没有用到保证线程安全的CAS技术
         */
        LinkedList linkedList = new LinkedList();
        linkedList.add("111");
        linkedList.add("333");
        linkedList.add("222");
        linkedList.add("444");
        linkedList.add("555");
        linkedList.add("111");
        System.out.println(linkedList.get(1));
        System.out.println(linkedList);

        /**
         * ArrayList所谓的动态扩容：其实就是创建一个新的数组，赋予新的长度，然后再覆盖掉原先的数组，进而实现
         * ArrayList底层是数组，查询块，增删慢  线程不安全，效率高
         * 线程不安全：方法里面既没有用到锁，也没有用到保证线程安全的CAS技术
         */
        ArrayList arrayList = new ArrayList();

        /**
         * Vector底层是数组，线程安全，效率低
         * 线程安全：对外提供的所有public方法都添加了synchronized修饰
         */
        Vector vector = new Vector();


        //Set 特点: 1.无序 (存储和取出的顺序）2.元素唯一
        /**
         * TreeSet底层是二叉树，保证元素排序 1.自然排序，让对像所属的类去实现comparable接口，无参构造。2.比较器接口comparator ,带参构造
         * 底层都是通过元素以键的形式保存到TreeMap的key,而它的值是一个final的new Object =null
         */
        TreeSet treeSet = new TreeSet();
        treeSet.add("111");
        treeSet.add("333");
        treeSet.add("222");
        treeSet.add("444");
        treeSet.add("555");
        treeSet.add("111");
        System.out.println(treeSet);

        /**
         * HashSet底层是哈希表，其实是hashMap，保证元素唯一性 hashCode() equals()
         */
        HashSet hashSet = new HashSet();



    }

}
