package com.story.storyadmin.manyThread.JUC.CAS;


/**
 * @author: lipan
 * @date: 2021年08月27日 3:29 下午
 * @description:
 */
public class Demo {


    /**
     * 一、什么是CAS？
     *     在计算机科学中，比较和交换（Conmpare And Swap）是用于实现多线程同步的原子指令。 它将内存位置的内容与给定值进行比较，只有在相同的情况下，将该内存位置的内容修改为新的给定值。 这是作为单个原子操作完成的。 原子性保证新值基于最新信息计算; 如果该值在同一时间被另一个线程更新，则写入将失败。 操作结果必须说明是否进行替换; 这可以通过一个简单的布尔响应（这个变体通常称为比较和设置），或通过返回从内存位置读取的值来完成（摘自维基本科）
     *
     *     JAVA1.5开始引入了CAS，主要代码都放在JUC的atomic包下
     *
     * 三、CAS在JUC中的运用
     *     我们看一下JUC中非常重要的一个类AbstractQueuedSynchronizer，作为JAVA中多种锁实现的父类，其中有很多地方使用到了CAS操作以提升并发的效率
     *
     *        private Node enq(final Node node) {
     *         for (;;) {
     *             Node t = tail;
     *             if (t == null) { // Must initialize
     *                 if (compareAndSetHead(new Node()))
     *                     tail = head;
     *             } else {
     *                 node.prev = t;
     *                 if (compareAndSetTail(t, node)) {
     *                     t.next = node;
     *                     return t;
     *                 }
     *             }
     *         }
     *     }
     *
     * 上图为同步队列的入队操作，也是一种乐观锁的实现，多线程情况下，操作头节点和尾节点都有可能失败，失败后会再次尝试，直到成功。
     *
     *四、ABA问题
     *   CAS可以有效的提升并发的效率，但同时也会引入ABA问题。
     *
     *   如线程1从内存X中取出A，这时候另一个线程2也从内存X中取出A，并且线程2进行了一些操作将内存X中的值变成了B，然后线程2又将内存X中的数据变成A，这时候线程1进行CAS操作发现内存X中仍然是A，然后线程1操作成功。虽然线程1的CAS操作成功，但是整个过程就是有问题的。比如链表的头在变化了两次后恢复了原值，但是不代表链表就没有变化。
     *
     *   所以JAVA中提供了AtomicStampedReference/AtomicMarkableReference来处理会发生ABA问题的场景，主要是在对象中额外再增加一个标记来标识对象是否有过变更。
     */
}
