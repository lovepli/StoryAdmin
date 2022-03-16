package com.story.storyadmin.myDatastucture.hashMapDemo.MyHashMap4;

/**
 * @author: 59688
 * @date: 2021/9/24
 * @description:
 */
public interface MyMap<K,V> {
    public V get(K key);
    public V put(K key,V value);

    interface IEntry<K,V>{
        public K getKey();
        public V getValue();
        //public V setValue(V value);
    }
}
