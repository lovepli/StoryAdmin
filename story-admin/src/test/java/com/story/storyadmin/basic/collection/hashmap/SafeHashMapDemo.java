package com.story.storyadmin.basic.collection.hashmap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: lipan
 * @date: 2019-06-04
 * @description:
 *
 * 问题；如何优化HashTable
 * 通过锁细颗粒度化，将整锁拆解成多个锁进行优化
 *
 * HashMap HashTable ConccurentHashMap 三者的区别；
 * HashMap 线程不安全，数组+链表+红黑树
 * HashTable 线程安全，锁住整个对象，数组+链表
 * ConccurentHashMap 线程安全 ，CAS+同步锁，数组+链表+红黑树
 *
 * HashMap的key,value 均可以为null,而其他的两个类不支持！！！
 */

public class SafeHashMapDemo {
    public static void main(String[] args) {
        Map hashMap = new HashMap<>();
        Map safeHashMap = Collections.synchronizedMap(hashMap);//使HashMap包装成线程安全的Map
        safeHashMap.put("aa", "1");
        safeHashMap.put("bb", "2");
        System.out.println(safeHashMap.get("bb"));
    }
}
