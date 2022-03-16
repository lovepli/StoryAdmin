package com.story.storyadmin.myDatastucture.hashMapDemo.HashMap2;


/**
 * @author: 59688
 * @date: 2021/9/24
 * @description: 定义一个接口，对外暴露快速存取的方法。
 *
 * 注意MyMap接口内部定义了一个内部接口Entry。
 */
public interface MyMap<K,V> {
     public V put(K k,V v);

     public V get(K k);

     interface  Entry<K,V>{
         public K getKey();
         public V getValue();
     }
}
