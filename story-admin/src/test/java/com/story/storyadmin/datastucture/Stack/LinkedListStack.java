package com.story.storyadmin.datastucture.Stack;

import com.story.storyadmin.datastucture.LinkedList.LinkedList;

/**
 * @author: lipan
 * @date: 2019-05-27
 * @description:v 链表栈
 */
public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> list;

    public LinkedListStack() {
        list = new LinkedList<>();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public  boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(E e) {
       list.addFirst(e);
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

    @Override
    public E peek() {
        return list.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: top ");
        res.append(list);
        return res.toString();
    }
}