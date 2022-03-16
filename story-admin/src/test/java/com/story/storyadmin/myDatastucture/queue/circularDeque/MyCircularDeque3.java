package com.story.storyadmin.myDatastucture.queue.circularDeque;

/**
 * 双向队列(加餐)
 */
public class MyCircularDeque3 {
    private int data[];// 数组容器
    private int front;// 头
    private int rear;// 尾
    private int maxsize;// 最大长度
    /*初始化 最大大小为k */
    public MyCircularDeque3(int k) {
        data = new int[k+1];
        front = 0;
        rear = 0;
        maxsize = k+1;
    }

    /** 头部插入 */
    public boolean insertFront(int value) {
        if(isFull())
            return false;
        else {
            front=(front+maxsize-1)%maxsize;
            data[front]=value;
        }
        return  true;
    }

    /** 尾部插入 */
    public boolean insertLast(int value) {
        if(isFull())
            return  false;
        else{
            data[rear]=value;
            rear=(rear+1)%maxsize;
        }
        return  true;
    }

    /** 正常头部删除 */
    public boolean deleteFront() {
        if (isEmpty())
            return false;
        else {
            front=(front+1)%maxsize;
        }
        return  true;
    }

    /** 尾部删除 */
    public boolean deleteLast() {
        if(isEmpty())
            return false;
        else {
            rear=(rear+maxsize-1)%maxsize;
        }
        return true;
    }

    /** Get the front item  */
    public int getFront() {
        if(isEmpty())
            return -1;
        return data[front];
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if(isEmpty())
            return -1;
        return  data[(rear-1+maxsize)%maxsize];
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return front==rear;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return (rear+1)%maxsize==front;
    }
}
