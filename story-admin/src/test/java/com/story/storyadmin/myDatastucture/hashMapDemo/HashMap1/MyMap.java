package com.story.storyadmin.myDatastucture.hashMapDemo.HashMap1;

/**
 * @author: 59688
 * @date: 2021/9/24
 * @description: 手写HashMap 定义一个Map接口
 */
public interface MyMap<K,V> {

    V put(K k,V v);

    V get(K k);

    int size();

    V remove(K k);

    boolean isEmpty();

    void clear();



}
