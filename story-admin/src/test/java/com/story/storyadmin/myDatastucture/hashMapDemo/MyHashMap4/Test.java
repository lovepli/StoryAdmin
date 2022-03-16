package com.story.storyadmin.myDatastucture.hashMapDemo.MyHashMap4;

/**
 * @author: 59688
 * @date: 2021/9/24
 * @description:
 */
public class Test {

    public static void main(String[] args) {
        MyHashMap mh=new MyHashMap();
        for (int i=0;i<100;i++){
            mh.put(i,i);
        }
        for (int i=0;i<100;i++){
           if (mh.get(i) !=null){
               System.out.println(mh.get(i));
           }
        }
    }
}
