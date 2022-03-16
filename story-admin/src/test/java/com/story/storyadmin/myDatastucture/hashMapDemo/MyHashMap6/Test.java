package com.story.storyadmin.myDatastucture.hashMapDemo.MyHashMap6;

/**
 * @author: 59688
 * @date: 2021/9/26
 * @description:
 */
public class Test {

    public static void main(String[] args) {
        MyHashMap map = new MyHashMap();
        map.put(1,1);
        map.put(2,2);
        map.put(1,40);
        map.put(2,200);

        System.out.println(map.get(1));
        System.out.println(map.get(2));

        map.remove(2);
        System.out.println(map.get(2));
    }

}
