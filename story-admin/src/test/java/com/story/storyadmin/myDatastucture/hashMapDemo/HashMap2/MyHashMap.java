package com.story.storyadmin.myDatastucture.hashMapDemo.HashMap2;

/**
 * @author: 59688
 * @date: 2021/9/24
 * @description: 手写一个迷你版 HashMap，面试随便问！ https://mp.weixin.qq.com/s/4XhzW9cKzFNlc0XsP67DuQ?
 */
public class MyHashMap<K,V> {

    final static int DEFAULT_CAPACITY = 16;
    final static float DEFAULT_LOAD_FACTOR = 0.75f;

    int capacity;
    float loadFactor;
    int size = 0;
    //HashMap的要素之一，就是数组，自然在这里，我们要定义数组，数组的初始化大小，还要考虑扩容的阀值。
    Entry<K,V>[] table;

    /**
     * 构造函数
     * 构造方法有什么好说的呢？
     * 仔细观察下，你会发现，其实这里使用到了“门面模式”。这里的2个构造方法其实指向的是同一个，但是对外却暴露了2个“门面”！
     */
    public MyHashMap(){
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int capacity, float loadFactor){
        if (capacity <0){
            throw new IllegalArgumentException("Illegal initial capacity:"+capacity);
        }
        if(loadFactor <=0 || Float.isNaN(loadFactor)){
            throw new IllegalArgumentException("Illegal initial factor:"+loadFactor);
        }
        //this.capacity = upperMinPowerOf2(capacity);
        this.size =capacity;
        this.loadFactor = loadFactor;
        this.table = new Entry[capacity];
    }

}
