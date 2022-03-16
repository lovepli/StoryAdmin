package com.story.storyadmin.myDatastucture.hashMapDemo.HashMap1;

/**
 * @author: 59688
 * @date: 2021/9/24
 * @description:
 */
public class Test {

    public static void main(String[] args) {
        MyMap<String,String> myMap = new MyHashMap<>();
        for (int i=0;i<6;i++){
            myMap.put("key"+i,"value"+i);
        }
        for (int i=0;i<6;i++){
            System.out.println("key"+i+", value is:"+myMap.get("key"+i));
        }
    }
}
