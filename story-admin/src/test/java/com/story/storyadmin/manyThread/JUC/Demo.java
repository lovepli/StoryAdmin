package com.story.storyadmin.manyThread.JUC;



/**
 * @author: lipan
 * @date: 2021年08月25日 2:34 下午
 * @description:  J.U.C 【java.util.concurrent包】知识点梳理
 *
 *      *
 *      *  java.util.concurrent（简称 JUC）包和其子包下的类和接口，它为 Java 的并发提供了各种功能支持，比如：
 *      *  1、线程执行器、线程池的创建类 Executors、ThreadPoolExecutor、 等；
 *      *  2、各种锁locks （Lock,ReadWriteLock,ReentrantLock可重入锁,ReentrantReadWriteLock共享锁）
 *      *  3、原子变量类atomic （AtomicInteger,AtomicLong,AtomicBoolean...）
 *      *  4、并发工具类-高级的线程同步结构 （闭锁CountDownLatch,栅栏CyclicBarrier,信号量Semaphore,交换器Exchanger）
 *      *  5、线程安全并发集合（ConcurrentHashMap、BlockingQueue,ArrayBlockingQueue,LinkedBlockingQueue,priorityBlockingQueue）
 *      *
 */
public class Demo {

    /**
     * java.util.concurrent包提供了并发编程的解决方案
     *
     * 1、CAS（Compare and Swap）比较并交换，是一种乐观锁的实现，是用非阻塞算法来代替锁定，其中
     * java.util.concurrent 包下的 AtomicInteger 就是借助 CAS 来实现的。但 CAS 也不是没有
     * 任何副作用，比如著名的 ABA 问题就是 CAS 引起的。
     *
     * 2、AQS 同步阻塞队列，是AbstractQueuedSynchronizer的简称，它提供了一种等待唤醒的机制。底层核数据结构是双端队列。
     *  采用自旋 + LockSupport + CAS来实现这种等待唤醒。在java的「JUC」并发包下很多类都是基于「AQS」实现的
     *
     *
     *  ABA 问题描述
     * 老宋去银行取钱，余额有 200 元，老宋取 100 元，但因为程序的问题，启动了两个线程，线程一和线程二进行比对扣款，线程一获取原本有 200 元，扣除 100 元，余额等于 100 元，此时阿里给老宋转账 100 元，于是启动了线程三抢先在线程二之前执行了转账操作，把 100 元又变成了 200 元，而此时线程二对比自己事先拿到的 200 元和此时经过改动的 200 元值一样，就进行了减法操作，把余额又变成了 100 元。这显然不是我们要的正确结果，我们想要的结果是余额减少了 100 元，又增加了 100 元，余额还是 200 元，而此时余额变成了 100 元，显然有悖常理，这就是著名的 ABA 的问题。
     *
     * 执行流程如下。
     *
     * 线程一：取款，获取原值 200 元，与 200 元比对成功，减去 100 元，修改结果为 100 元。
     * 线程二：取款，获取原值 200 元，阻塞等待修改。
     * 线程三：转账，获取原值 100 元，与 100 元比对成功，加上 100 元，修改结果为 200 元。
     * 线程二：取款，恢复执行，原值为 200 元，与 200 元对比成功，减去 100 元，修改结果为 100 元。
     * 最终的结果是 100 元。
     *
     * ABA 问题的解决
     * 常见解决 ABA 问题的方案加版本号，来区分值是否有变动。以老宋取钱的例子为例，如果加上版本号，执行流程如下。
     *
     * 线程一：取款，获取原值 200_V1，与 200_V1 比对成功，减去 100 元，修改结果为 100_V2。
     * 线程二：取款，获取原值 200_V1 阻塞等待修改。
     * 线程三：转账，获取原值 100_V2，与 100_V2 对比成功，加 100 元，修改结果为 200_V3。
     * 线程二：取款，恢复执行，原值 200_V1 与现值 200_V3 对比不相等，退出修改。
     * 最终的结果为 200 元，这显然是我们需要的结果。
     * 在程序中，要怎么解决 ABA 的问题呢？
     * 在 JDK 1.5 的时候，Java 提供了一个 AtomicStampedReference 原子引用变量，通过添加版本号来解决 ABA 的问题，具体使用示例如下：
     *
     * String name = "老宋";
     * String newName = "Java";
     * AtomicStampedReference<String> as = new AtomicStampedReference<String>(name, 1);
     * System.out.println("值：" + as.getReference() + " | Stamp：" + as.getStamp());
     * as.compareAndSet(name, newName, as.getStamp(), as.getStamp() + 1);
     * System.out.println("值：" + as.getReference() + " | Stamp：" + as.getStamp());
     * 以上程序执行结果如下：
     *
     * 值：老宋 | Stamp：1
     *
     * 值：Java | Stamp：2
     */

    /**
     *并发编程各种锁：
     *
     * 乐观锁和悲观锁
     * 悲观锁和乐观锁并不是某个具体的“锁”而是一种并发编程的基本概念。乐观锁和悲观锁最早出现在数据库的设计当中，后来逐渐被Java的并发包所引入。
     *
     * 悲观锁
     * 悲观锁认为对于同一个数据的并发操作，- -定是会发生修改的，哪怕没有修改，也会认为修改。因此对于同一个数据的并发操作,悲观锁采取加锁的形式。悲观地认为,不加锁的并发操作一定会出问题。
     *
     * 乐观锁
     * 乐观锁正好和悲观锁相反，它获取数据的时候，并不担心数据被修改，每次获取数据的时候也不会加锁，只是在更新数据的时候,通过判断现有的数据 是否和原数据一致来判断数据是 否被其他线程操作,如果没被其他线程修改则进行数据更新,如果被其他线程修改则不进行数据更新。
     *
     * 公平锁和非公平锁
     * 根据线程获取锁的抢占机制，锁又可以分为公平锁和非公平锁。
     *
     * 公平锁
     * 公平锁是指多个线程按照申请锁的顺序来获取锁。
     *
     * 非公平锁
     * 非公平锁是指多个线程获取锁的顺序并不是按照申请锁的顺序，有可能后申请的线程比先申请的线程优先获取锁。
     * ReentrantLock提供了公平锁和非公平锁的实现。
     * 公平锁: new ReentrantLock(true)
     * 非公平锁: new ReentrantLock(false)
     * 如果构造函数不传任何参数的时候，默认提供的是非公平锁。
     *
     * 独占锁和共享锁
     * 根据锁能否被多个线程持有，可以把锁分为独占锁和共享锁。
     *
     * 独占锁
     * 独占锁是指任何时候都只有一个线程能执行资源操作。
     *
     * 共享锁
     * 共享锁指定是可以同时被多个线程读取，但只能被一个线程修改。
     *
     * 可重入锁
     * 可重入锁指的是该线程获取了该锁之后，可以无限次的进入该锁锁住的代码。
     *
     * 自旋锁
     * 自旋锁是指尝试获取锁的线程不会立即阻塞，而是采用循环的方式去尝试获取锁，这样的好处是减少线程上下文切换的消耗，缺点是循环会消耗 CPU。
     */


}
