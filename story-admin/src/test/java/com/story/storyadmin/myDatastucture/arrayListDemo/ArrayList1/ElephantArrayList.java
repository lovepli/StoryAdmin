package com.story.storyadmin.myDatastucture.arrayListDemo.ArrayList1;

import java.util.Arrays;

/**
 * @author: lipan
 * @date: 2021年09月26日 12:03 上午
 * @description:
 */
public class ElephantArrayList {
    private Object[] elementData;
    private int size;
    // 构造器
    public ElephantArrayList() {
        this.elementData = new Object[10];
    }
    public int size() {
        return size;
    }

    // 添加元素
    public boolean add(Object obj) {
        ensureCapacityInternal(size + 1);
        elementData[size++] = obj;
        return true;
    }
    //检查容量，变长数组的原因就是这里
    private void ensureCapacityInternal(int minCapacity) {
        if (minCapacity - elementData.length > 0) {
            // 需要的最小容量已经大于了数组的长度，此时需要扩容
            int oldCapacity = elementData.length;
            int newCapacity = (oldCapacity * 3) / 2;
            // 如果扩容1.5倍还不够，则将传入的长度为新长度
            if (newCapacity - minCapacity < 0)
                newCapacity = minCapacity;
            //进行一次数组拷贝，将旧内容全部拷贝到新数组中
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    public Object get(int index) {
        rangeCheck(index);
        return elementData[index];
    }
    // 检查给定下标是否符合规则
    private void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("下标有误，Index:" + index + ",size:" + size);
        }
    }

    // 重载add方法通过下标来插入
    public boolean add(int index, Object obj) {
        rangeCheck(index);
        ensureCapacityInternal(size + 1);
        // 数组拷贝，第1个参数是源数组，第2个参数是源数组起始位置，第3个参数是目标数组，第4个参数是目标数组的起始位置，最后一个参数是要移动的长度。
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = obj;
        size++;
        return true;
    }
}
