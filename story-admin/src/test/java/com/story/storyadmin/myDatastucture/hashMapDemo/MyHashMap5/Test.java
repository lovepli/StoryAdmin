package com.story.storyadmin.myDatastucture.hashMapDemo.MyHashMap5;

/**
 * @author: lipan
 * @date: 2021年09月25日 11:59 下午
 * @description:
 */
public class Test {

    public static void main(String[] args) {
        ElephantHashMap mh= new ElephantHashMap();
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
