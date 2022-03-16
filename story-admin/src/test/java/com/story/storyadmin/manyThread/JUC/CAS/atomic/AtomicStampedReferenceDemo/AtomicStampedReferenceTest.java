package com.story.storyadmin.manyThread.JUC.CAS.atomic.AtomicStampedReferenceDemo;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author: lipan
 * @date: 2021年08月27日 12:26 下午
 * @description:
 */
public class AtomicStampedReferenceTest {


    public static void main(String[] args) {

        String str1 = "aaa";
        String str2 = "bbb";
        AtomicStampedReference<String> reference = new AtomicStampedReference<String>(str1, 1);
        reference.compareAndSet(str1, str2, reference.getStamp(), reference.getStamp() + 1);
        System.out.println("reference.getReference() = " + reference.getReference());

        boolean b = reference.attemptStamp(str2, reference.getStamp() + 1);
        System.out.println("b: " + b);
        System.out.println("reference.getStamp() = " + reference.getStamp());

        boolean c = reference.weakCompareAndSet(str2, "ccc", 4, reference.getStamp() + 1);
        System.out.println("reference.getReference() = " + reference.getReference());
        System.out.println("c = " + c);
    }

    /**
     * 我们都知道在使用CAS也就是使用compareAndSet（current，next）方法进行无锁自加或者更换栈的表头之类的
     * 问题时会出现ABA问题，Java中使用AtomicStampedReference来解决CAS中的ABA问题，它不再像compareAndSet方法
     * 中只比较内存中的值与当前值是否相等，而且先比较引用是否相等，然后比较值是否相等，这样就避免了ABA问题。
     * 那么AtomicStampedReference的基本用法是什么呢？看如下：
     * //构造方法, 传入引用和戳
     * public AtomicStampedReference(V initialRef, int initialStamp)
     * //返回引用
     * public V getReference()
     * //返回版本戳
     * public int getStamp()
     * //如果当前引用 等于 预期值并且 当前版本戳等于预期版本戳, 将更新新的引用和新的版本戳到内存
     * public boolean compareAndSet(V   expectedReference,
     *                                  V   newReference,
     *                                  int expectedStamp,
     *                                  int newStamp)
     * //如果当前引用 等于 预期引用, 将更新新的版本戳到内存
     * public boolean attemptStamp(V expectedReference, int newStamp)
     * //设置当前引用的新引用和版本戳
     * public void set(V newReference, int newStamp)
     */
}
