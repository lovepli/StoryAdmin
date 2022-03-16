package com.story.storyadmin.manyThread.JUC.CAS.atomic.atomicReferenceDemo;

/**
 * @author: lipan
 * @date: 2021年08月27日 10:32 上午
 * @description:
 */
public class Demo {

    /**
     * 高并发编程之AtomicReference讲解 https://blog.csdn.net/weixin_42146366/article/details/87822781
     *
     * 一、AtomicReference介绍
     * ①.AtomicReference和AtomicInteger非常类似，不同之处就在于AtomicInteger是对整数的封装，而AtomicReference则对应普通的对象引用。也就是它可以保证你在修改对象引用时的线程安全性。
     *
     * ②.AtomicReference是作用是对”对象”进行原子操作。 提供了一种读和写都是原子性的对象引用变量。原子意味着多个线程试图改变同一个AtomicReference(例如比较和交换操作)将不会使得AtomicReference处于不一致的状态。
     *
     * 二、AtomicReference几个常用方法
     *
     * 由于AtomicReference是对一个对象进行操作，所以先创建一个对象类(JavaBean)：SimpleObject类
     */

    /**
     * AtomicReference和AtomicStampedReference的区别 https://blog.csdn.net/zxl1148377834/article/details/90079882
     *
     * 这里的compareAndSet方法即cas操作本身是原子的，但是在某些场景下会出现异常场景
     *
     * 此处说一下ABA问题：
     *
     *  线程A     线程B
     *    原值 ①
     *        ｜
     *        ② --修改为2
     *        ｜
     *  读取-- ① --又修改为1
     *   误以为未修改
     *
     *
     * 比如，我们只是简单得要做一个数值加法，即使在我取得期望值后，这个数字被不断的修改，只要它最终改回了我的期望值，我的加法计算就不会出错。也就是说，当你修改的对象没有过程的状态信息，所有的信息都只保存于对象的数值本身。
     *
     * 但是，在现实中，还可能存在另外一种场景。就是我们是否能修改对象的值，不仅取决于当前值，还和对象的过程变化有关，这时，AtomicReference就无能为力了。
     *
     * AtomicStampedReference与AtomicReference的区别：
     * AtomicStampedReference它内部不仅维护了对象值，还维护了一个时间戳（我这里把它称为时间戳，实际上它可以使任何一个整数，它使用整数来表示状态值）。当AtomicStampedReference对应的数值被修改时，除了更新数据本身外，还必须要更新时间戳。当AtomicStampedReference设置对象值时，对象值以及时间戳都必须满足期望值，写入才会成功。因此，即使对象值被反复读写，写回原值，只要时间戳发生变化，就能防止不恰当的写入。

     */
}
