package com.story.storyadmin.myDatastucture.linkedListDemo.LinkedList1;

/**
 * @author: lipan
 * @date: 2021年09月26日 12:09 上午
 * @description:
 */
public class Test {
    public static void main(String[] args) {
        ElephantLinkedList list = new ElephantLinkedList();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        System.out.println(list.size());
        list.remove(1);
        System.out.println(list.size());
        list.add(1,"ddd");
        System.out.println(list.size());
        //我们没实现迭代器接口，所以用个普通for循环看一下元素吧
        //aaa,bbb,newString,ccc,ddd,
        for(int i=0;i<list.size();i++){
            System.out.print(list.get(i)+",");
        }
    }
}
