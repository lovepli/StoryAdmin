package com.story.storyadmin.myDatastucture.arrayListDemo.ArrayList1;

/**
 * @author: lipan
 * @date: 2021年09月26日 12:05 上午
 * @description:
 */
public class Test {

    public static void main(String[] args) {
        ElephantArrayList list = new ElephantArrayList();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        list.add("ddd");
        list.add(2,"newString");
        System.out.println(list.size());
        //我们没实现迭代器接口，所以用个普通for循环看一下元素吧
        for(int i=0;i<list.size();i++){
            System.out.print(list.get(i)+",");
        }
    }
}
