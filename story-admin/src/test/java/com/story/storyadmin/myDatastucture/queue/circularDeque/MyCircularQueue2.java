package com.story.storyadmin.myDatastucture.queue.circularDeque;

/**
 * 循环队列(链表实现)
 */
public class MyCircularQueue2 {
    class node {
        int data;// 节点的结果
        node next;// 下一个连接的节点
        public node() {}
        public node(int data) {
            this.data = data;
        }
    }
    node front;//相当于head 带头节点的
    node rear;//相当于tail/end
    int maxsize;//最大长度
    int len=0;
    public MyCircularQueue2(int k) {
        front=new node(0);
        rear=front;
        maxsize=k;
        len=0;
    }
    public boolean enQueue(int value)  {
        if (isFull())
            return  false;
        else {
            node va=new node(value);
            rear.next=va;
            rear=va;
            len++;
        }
        return  true;
    }
    public boolean deQueue() {
        if (isEmpty())
            return false;
        else {
            front.next=front.next.next;
            len--;
            //注意 如果被删完 需要将rear指向front
            if(len==0)
                rear=front;
        }
        return  true;
    }

    public int Front() {
        if(isEmpty())
            return -1;
        return front.next.data;
    }

    public int Rear() {
        if(isEmpty())
            return -1;
        return rear.data;
    }

    public boolean isEmpty() {
        return  len==0;
        //return rear == front;
    }

    public boolean isFull() {
        return len==maxsize;
    }
}