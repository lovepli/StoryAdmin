package com.story.storyadmin.basic.dataStruct.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: lipan
 * @date: 2019-06-12
 * @description:
 *
 * Queue
 * 队列(Queue)是一种先进先出(FIFO)的数据结构
 * 实现类:ArrayDeque，LinkedList
 *
 * 操作Queue的元素的方法:
 * 添加至队尾压栈:add() / offer()
 * 获取队列头部元素并删除:E remove() / E poll()
 * 获取队列头部元素但不删除:E element() / E peek()
 * 两组方法的区别:是否抛出Exception
 *
 * 避免把null添加到队列
 */
public class QueueDemo {
    public static void main(String[] args) {
        Queue<Person> queue = new LinkedList<>();  //LinkedList实现Deque接口，然后Deque接口又继承Queue接口
        queue.offer(new Person("Ming", 11));
        queue.offer(new Person("Hong", 12));
        queue.offer(new Person("Jun", 13));
        System.out.println( queue.poll());
        System.out.println( queue.poll());
        System.out.println( queue.poll());
        //System.out.println( queue.poll()); //null

        //存入3个元素，取出4个元素
//        (Person: Ming, 11)
//        (Person: Hong, 12)
//        (Person: Jun, 13)
//        null
        if (!queue.isEmpty()) { //判断是否为空
            System.out.println(queue.remove());
        }



    }
}
