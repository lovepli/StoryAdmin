package com.story.storyadmin.myDatastucture.hashMapDemo.HashMap2;

/**
 * @author: 59688
 * @date: 2021/9/24
 * @description: HashMap的要素之一，单链表的体现就在这里！
 */
public class Entry<K,V> implements MyMap.Entry<K,V> {
    private K key;
    private V value;
    private Entry<K,V> next;

    public Entry(){}

    public Entry(K key, V value, Entry<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }
}
