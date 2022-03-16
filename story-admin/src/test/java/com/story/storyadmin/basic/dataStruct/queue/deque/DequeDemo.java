package com.story.storyadmin.basic.dataStruct.queue.deque;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author: lipan
 * @date: 2019-06-12
 * @description:
 *
 * Deque
 * Deque实现一个双端队列(Double Ended Queue): 既可以添加到队尾，也可以添加到队首
 * 既可以从队首获取，又可以从队尾获取
 * 添加元素到队尾:addLast(E e) / offerLast(E e)
 * 取队首元素并删除:E removeFirst() / E pollFirst()
 * 取队首元素但不删除:E getFirst() / E peekFirst()
 * 总是调用xxxFirst / xxxLast以便与Queue的方法区分开
 */
public class DequeDemo {
    public static void main(String[] args) {
        Deque<String> deque = new LinkedList<>();
        deque.offerLast("end");
        deque.offerFirst("C");
        deque.offerFirst("B");
        deque.offerFirst("A");
        System.out.println(deque.pollLast());
        System.out.println(deque.pollFirst());
        System.out.println(deque.pollFirst());
        System.out.println(deque.pollFirst());
        System.out.println(deque.pollFirst());

        //end
        //A
        //B
        //C
        //null
    }
}
