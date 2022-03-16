package com.story.storyadmin.myDatastucture.hashMapDemo.MyHashMap6;

/**
 * @author: 59688
 * @date: 2021/9/26
 * @description: https://mp.weixin.qq.com/s/QPTU-Dy1lAnfU-GkUsy2lg
 * 手写一个hashMap:
 * 1、实现数组 + 链表
 * 2、实现获取Key对应数组索引的方法
 * 3、实现get方法
 * 4、实现put方法
 * 5、实现remove方法
 */
public class MyHashMap {

    static class Node {
        int key, value; //保存该节点的Key、Value
        Node next; //指向下一个节点

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    //链表有了，就再来个数组（面试过程基本上不要求扩容，所以我们就直接给数组定义一个差不多的值就OK了）
    private final int CAPACITY = 10000;
    Node[] nodes = new Node[CAPACITY];

    /**
     * 实现put方法
     * 流程介绍
     * 注意：我们需要保存前一个节点，这样如果put的是一个新键值对的话，我们可以获取到链表的最后一个不为null的节点
     *
     * 1、获取Key对应的索引值
     * 2、非空判断，如果为空，说明该索引对应的链表为空，可直接创建新节点添加
     * 3、不为空则循环遍历，遍历过程更新prev ，如果遍历过程中找到则返回value值
     * 4、如果遍历完成还没有返回，说明没有该节点可以添加，那么根据 prev 是否为null进行添加；
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        int idx = getIndex(key);
        Node now = nodes[idx], tmp = now;
        if (tmp != null) {
            Node prev = null;
            while (tmp != null) {
                if (tmp.key == key) {
                    tmp.value = value;
                    return;
                }
                prev = tmp;
                tmp = tmp.next;
            }
            tmp = prev;
        }

        Node node = new Node(key, value);
        if (tmp != null) {
            tmp.next = node;
        } else {
            nodes[idx] = node;
        }
    }

    /**
     * 实现get方法
     * 流程很简单:
     * 1、获取到传入的 key 的对应的索引下标
     * 2、拿到对应下标对应的链表首节点
     * 3、非空判断
     * 4、如果链表首节点的Key是目标Key，那么直接返回对应的Value值；如果不是，那么对链表进行遍历获取，如果遍历完成都没有去返回Value值，那么说明HashMap没有这个数据，那么就返回-1.
     * @param key
     * @return
     */
    public int get(int key) {
        int idx = getIndex(key);
        Node now = nodes[idx];

        if (now != null) {
            if (now.key == key) {
                return now.value;
            } else {
                while (now != null) {
                    if (now.key == key) {
                        return now.value;
                    }
                    now = now.next;
                }
            }
        }

        return -1;
    }

    /**
     * 大致流程跟get方法差不多，区别就是我们我们需要保存需要删除节点的前一个节点
     * @param key
     */
    public void remove(int key) {
        int idx = getIndex(key);
        Node now = nodes[idx];

        if (now != null) {
            Node prev = null;
            while (now != null) {
                if (now.key == key) {
                    if (prev != null) {
                        prev.next = now.next;
                    } else {
                        nodes[idx] = now.next;
                    }
                    now.next = null;
                    return;
                }
                prev = now;
                now = now.next;
            }
        }
    }

    /**
     * 实现获取Key对应数组索引的方法
     * @param key
     * @return
     */
    private int getIndex(int key) {
        //实现:因为int为基本数据类型，所以我们用Integer.hashCode(int value),而Integer.hashCode(int value)，返回的其实就是你传入的值
        int hash = Integer.hashCode(key);
        //高16位异或低16位
        hash ^= (hash >>> 16);
        ////与数组长度取模，得到对应的索引下标
        return hash % CAPACITY;
    }

}