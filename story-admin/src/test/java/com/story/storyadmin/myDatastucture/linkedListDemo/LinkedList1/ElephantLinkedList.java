package com.story.storyadmin.myDatastucture.linkedListDemo.LinkedList1;

/**
 * @author: lipan
 * @date: 2021年09月26日 12:07 上午
 * @description:
 */
public class ElephantLinkedList {
    // 第一个节点
    Node first;
    // 最后一个节点
    Node last;
    private int size = 0;
    // 默认构造器
    public ElephantLinkedList() {
    }
    public int size() {
        return size;
    }
    private static class Node {
        Node prev;
        Object obj;
        Node next;
        // 存储单元构造器，为方便添加元素
        public Node(Node prev, Object obj, Node next) {
            this.prev = prev;
            this.obj = obj;
            this.next = next;
        }
    }

    // 添加元素
    public boolean add(Object obj) {
        Node L = last;// 当前链表的最后一个节点暂放到变量L（添加元素前）
        Node newNode = new Node(L, obj, null);
        last = newNode;// 末尾添加，所以将待插入元素赋给last
        if (null == L) {// 此时说明是个空链表
            // 我们将first也置为newNode
            first = newNode;
        } else {
            // 将插入前的末尾节点的next置为newNode，这样，我们的链表就连起来了
            L.next = newNode;
        }
        size++;
        return true;
    }

    // 查找元素
    public Object get(int index) {
        Node temp = findNode(index);
        return temp.obj;
    }
    // 寻址
    private Node findNode(int index) {
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("下标有误，Index:" + index + ",size:" + size);
        }
        Node temp = null;
        if (first != null) {
            // 位于链表前半部分则从头开始向后找
            if (index < size/2){
                temp = first;
                for (int i = 0; i < index; i++) {
                    temp = temp.next;
                }
            }else {// 位于链表后半部分则从尾开始向前找
                temp = last;
                for (int i = size-1; i > index; i--) {
                    temp = temp.prev;
                }
            }
        }
        return temp;
    }

    // 删除元素（重点是解除待删除元素的前后节点的链接关系）
    public Object remove(int index) {
        Node temp = findNode(index);// temp是待删除元素
        Node prev = temp.prev;
        Node next = temp.next;
        // 先处理前一个节点的链接
        if (prev == null) {
            // 将next赋给first即可断开temp的链接
            first = next;
        } else {
            // 将前元素与后元素链接
            prev.next = next;
            // 与前元素断开链接
            temp.prev = null;
        }
        // 再处理后一个节点的链接（原理同处理前一个节点）
        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            temp.next = null;
        }
        size--;
        return temp.obj;
    }


    // 插入元素
    public boolean add(int index ,Object obj){
        if (index == size){
            add(obj);
        }else{
            Node next = findNode(index);// 当前位置的元素会变成待插入元素的下一个
            Node prev = next.prev;
            Node newNode = new Node(prev, obj, next);
            // 然后我们处理这三个元素的连接关系即可
            next.prev = newNode;
            if (prev == null){
                first = newNode;
            }else {
                prev.next = newNode;
            }
            size++;
        }
        return true;
    }
}
