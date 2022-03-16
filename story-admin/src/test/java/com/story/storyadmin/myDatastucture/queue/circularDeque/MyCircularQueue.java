package com.story.storyadmin.myDatastucture.queue.circularDeque;

/**
 * 写各种队列  https://mp.weixin.qq.com/s/jt5kpy9yjro8VYcBkHIPEw
 * 循环队列(数组实现)
 */
public class MyCircularQueue {
    private int data[];// 数组容器
    private int front;// 头
    private int rear;// 尾
    private int maxsize;// 最大长度
    public MyCircularQueue(int k) {
        data = new int[k+1];
        front = 0;
        rear = 0;
        maxsize = k+1;
    }

    public boolean enQueue(int value)  {
        if (isFull())
            return  false;
        else {
            data[rear] = value;
            rear=(rear + 1) % maxsize;
        }
        return  true;
    }

    public boolean deQueue() {
        if (isEmpty())
            return false;
        else {
            front=(front+1)%maxsize;
        }
        return  true;
    }

    public int Front() {
        if(isEmpty())
            return -1;
        return data[front];
    }

    public int Rear() {
        if(isEmpty())
            return -1;
        return data[(rear-1+maxsize)%maxsize];
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public boolean isFull() {
        return (rear + 1) % maxsize == front;
    }
}
