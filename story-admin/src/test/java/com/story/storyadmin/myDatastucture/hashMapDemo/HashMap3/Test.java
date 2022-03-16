package com.story.storyadmin.myDatastucture.hashMapDemo.HashMap3;

/**
 * @author: 59688
 * @date: 2021/9/24
 * @description:
 */
public class Test {

    public static void main(String[] args) {
        MyHashMap<String,String> myHashMap =new MyHashMap<>();
        myHashMap.put("张三","22");
        myHashMap.put("李四","23");
       // myHashMap.put("王五","24"); // 有问题！！
        System.out.println(myHashMap.get("张三"));
    }
}
