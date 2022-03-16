> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s?__biz=MzAwOTIwMDEyNw==&mid=2247483823&idx=1&sn=c8747f68fc57d2bac4a75a73f28347d2&chksm=9b62093fac158029d51e7e8e38a5ab9ec6586ab73315f3a9c6364da3a5e45f913fe327e4607f&scene=21#wechat_redirect)

Java 中的并发包指的是 java.util.concurrent（简称 JUC）包和其子包下的类和接口，它为 Java 的并发提供了各种功能支持，比如：

*   提供了线程池的创建类 ThreadPoolExecutor、Executors 等；

*   提供了各种锁，如 Lock、ReentrantLock 等；

*   提供了各种线程安全的数据结构，如 ConcurrentHashMap、LinkedBlockingQueue、DelayQueue 等；

*   提供了更加高级的线程同步结构，如 CountDownLatch、CyclicBarrier、Semaphore 等。


在前面的章节中我们已经详细地介绍了线程池的使用、线程安全的数据结构等，本文我们就重点学习一下 Java 并发包中更高级的线程同步类：CountDownLatch、CyclicBarrier、Semaphore 和 Phaser 等。

CountDownLatch 介绍和使用
--------------------

CountDownLatch（闭锁）可以看作一个只能做减法的计数器，可以让一个或多个线程等待执行。  
CountDownLatch 有两个重要的方法：

*   countDown()：使计数器减 1；

*   await()：当计数器不为 0 时，则调用该方法的线程阻塞，当计数器为 0 时，可以唤醒等待的一个或者全部线程。


CountDownLatch 使用场景：

以生活中的情景为例，比如去医院体检，通常人们会提前去医院排队，但只有等到医生开始上班，才能正式开始体检，医生也要给所有人体检完才能下班，这种情况就要使用 CountDownLatch，流程为：患者排队 → 医生上班 → 体检完成 → 医生下班。

CountDownLatch 示例代码如下：

```
// 医院闭锁
CountDownLatch hospitalLatch = new CountDownLatch(1);
// 患者闭锁
CountDownLatch patientLatch = new CountDownLatch(5);
System.out.println("患者排队");
ExecutorService executorService = Executors.newCachedThreadPool();
for (int i = 0; i < 5; i++) {
    final int j = i;
    executorService.execute(() -> {
        try {
            hospitalLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("体检：" + j);
        patientLatch.countDown();
    });
}
System.out.println("医生上班");
hospitalLatch.countDown();
patientLatch.await();
System.out.println("医生下班");
executorService.shutdown();
```

以上程序执行结果如下：

```
患者排队

医生上班

体检：4

体检：0

体检：1

体检：3

体检：2

医生下班
```

执行流程如下图：

![](https://mmbiz.qpic.cn/sz_mmbiz_png/nYhc8O2Qc7gIaCibsiaibSaCsuhrFHnFSeq8ZQq3nCkd0UneUvCNUMSQ9Gr3bOibBibC9kRCLuRfOdnDEQjgib0GicGZA/640?wx_fmt=png)

CyclicBarrier 介绍和使用
-------------------

CyclicBarrier（循环屏障）通过它可以实现让一组线程等待满足某个条件后同时执行。

CyclicBarrier 经典使用场景是公交发车，为了简化理解我们这里定义，每辆公交车只要上满 4 个人就发车，后面来的人都会排队依次遵循相应的标准。

它的构造方法为 CyclicBarrier(int parties,Runnable barrierAction) 其中，parties 表示有几个线程来参与等待，barrierAction 表示满足条件之后触发的方法。CyclicBarrier 使用 await() 方法来标识当前线程已到达屏障点，然后被阻塞。

CyclicBarrier 示例代码如下：

```
import java.util.concurrent.*;
public class CyclicBarrierTest {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4, new Runnable() {
            @Override
            public void run() {
                System.out.println("发车了");
            }
        });
        for (int i = 0; i < 4; i++) {
            new Thread(new CyclicWorker(cyclicBarrier)).start();
        }
    }
    static class CyclicWorker implements Runnable {
        private CyclicBarrier cyclicBarrier;
        CyclicWorker(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }
        @Override
        public void run() {
            for (int i = 0; i < 2; i++) {
                System.out.println("乘客：" + i);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
```

以上程序执行结果如下：

```
乘客：0

乘客：0

乘客：0

乘客：0

发车了

乘客：1

乘客：1

乘客：1

乘客：1

发车了
```

执行流程如下图：

![](https://mmbiz.qpic.cn/sz_mmbiz_png/nYhc8O2Qc7gIaCibsiaibSaCsuhrFHnFSeqItiaoaxtUnwCr5bw5S761xvzrec1sYlIciaMFXX5PxlBWOfIDJ4Qp0yw/640?wx_fmt=png)

Semaphore 介绍和使用
---------------

Semaphore（信号量）用于管理多线程中控制资源的访问与使用。Semaphore 就好比停车场的门卫，可以控制车位的使用资源。比如来了 5 辆车，只有 2 个车位，门卫可以先放两辆车进去，等有车出来之后，再让后面的车进入。

Semaphore 示例代码如下：

```
Semaphore semaphore = new Semaphore(2);
ThreadPoolExecutor semaphoreThread = new ThreadPoolExecutor(10, 50, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
for (int i = 0; i < 5; i++) {
    semaphoreThread.execute(() -> {
        try {
            // 堵塞获取许可
            semaphore.acquire();
            System.out.println("Thread：" + Thread.currentThread().getName() + " 时间：" + LocalDateTime.now());
            TimeUnit.SECONDS.sleep(2);
            // 释放许可
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    });
}
复制
以上程序执行结果如下：

Thread：pool-1-thread-1 时间：2019-07-10 21:18:42

Thread：pool-1-thread-2 时间：2019-07-10 21:18:42

Thread：pool-1-thread-3 时间：2019-07-10 21:18:44

Thread：pool-1-thread-4 时间：2019-07-10 21:18:44

Thread：pool-1-thread-5 时间：2019-07-10 21:18:46
```

执行流程如下图：

![](https://mmbiz.qpic.cn/sz_mmbiz_png/nYhc8O2Qc7gIaCibsiaibSaCsuhrFHnFSeqX6DdmvoWzDDlT7LMwod8oEfN3xUfsDBiaqNnb8Lncoy8cm3UiaqukLgA/640?wx_fmt=png)

Phaser 介绍和使用
------------

Phaser（移相器）是 JDK 7 提供的，它的功能是等待所有线程到达之后，才继续或者开始进行新的一组任务。

比如有一个旅行团，我们规定所有成员必须都到达指定地点之后，才能发车去往景点一，到达景点之后可以各自游玩，之后必须全部到达指定地点之后，才能继续发车去往下一个景点，类似这种场景就非常适合使用 Phaser。

Phaser 示例代码如下：

```
public class Lesson5_6 {
    public static void main(String[] args) throws InterruptedException {
        Phaser phaser = new MyPhaser();
        PhaserWorker[] phaserWorkers = new PhaserWorker[5];
        for (int i = 0; i < phaserWorkers.length; i++) {
            phaserWorkers[i] = new PhaserWorker(phaser);
            // 注册 Phaser 等待的线程数，执行一次等待线程数 +1
            phaser.register();
        }
        for (int i = 0; i < phaserWorkers.length; i++) {
            // 执行任务
            new Thread(new PhaserWorker(phaser)).start();
        }
    }
    static class PhaserWorker implements Runnable {
        private final Phaser phaser;
        public PhaserWorker(Phaser phaser) {
            this.phaser = phaser;
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " | 到达" );
            phaser.arriveAndAwaitAdvance(); // 集合完毕发车
            try {
                Thread.sleep(new Random().nextInt(5) * 1000);
                System.out.println(Thread.currentThread().getName() + " | 到达" );
                phaser.arriveAndAwaitAdvance(); // 景点 1 集合完毕发车
                Thread.sleep(new Random().nextInt(5) * 1000);
                System.out.println(Thread.currentThread().getName() + " | 到达" );
                phaser.arriveAndAwaitAdvance(); // 景点 2 集合完毕发车
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    // Phaser 每个阶段完成之后的事件通知
    static class MyPhaser extends  Phaser{
        @Override
        protected boolean onAdvance(int phase, int registeredParties) { // 每个阶段执行完之后的回调
            switch (phase) {
                case 0:
                    System.out.println("==== 集合完毕发车 ====");
                    return false;
                case 1:
                    System.out.println("==== 景点1集合完毕，发车去下一个景点 ====");
                    return false;
                case 2:
                    System.out.println("==== 景点2集合完毕，发车回家 ====");
                    return false;
                default:
                    return true;
            }
        }
    }
}
```

以上程序执行结果如下：

```
Thread-0 | 到达

Thread-4 | 到达

Thread-3 | 到达

Thread-1 | 到达

Thread-2 | 到达

==== 集合完毕发车 ====

Thread-0 | 到达

Thread-4 | 到达

Thread-1 | 到达

Thread-3 | 到达

Thread-2 | 到达

==== 景点1集合完毕，发车去下一个景点 ====

Thread-4 | 到达

Thread-3 | 到达

Thread-2 | 到达

Thread-1 | 到达

Thread-0 | 到达

==== 景点2集合完毕，发车回家 ====
```

执行流程如下图：

![](https://mmbiz.qpic.cn/sz_mmbiz_png/nYhc8O2Qc7gIaCibsiaibSaCsuhrFHnFSeqjHOWED6OgUVwib4YzjqQFjMmxsoiaU2ohjUPU5SXsic8xK115iaqujUJUQ/640?wx_fmt=png)

推荐阅读：

[JAVA 并发编程（五）线程安全之 synchronized 和 ReentrantLock](http://mp.weixin.qq.com/s?__biz=MzAwOTIwMDEyNw==&mid=2247483812&idx=1&sn=c84bd4acc361b5a6851e8f00343ab3d7&chksm=9b620934ac158022eefc3ebf21cb5f713f2615ba3c850223d4c3250429e0af0fff61c1ce7bdb&scene=21#wechat_redirect)

[JAVA 并发编程（四）什么是 ThreadLocal，它有什么用?](http://mp.weixin.qq.com/s?__biz=MzAwOTIwMDEyNw==&mid=2247483804&idx=1&sn=4159a0bbddc3fce70f9d62e420ee0696&chksm=9b62090cac15801af3c541acbcb2e2e1eba141fa2bb0d488882bb361d68db9874716de5d63b3&scene=21#wechat_redirect)

[JAVA 并发编程（三）线程池之 Executors](http://mp.weixin.qq.com/s?__biz=MzAwOTIwMDEyNw==&mid=2247483790&idx=1&sn=7182076448b53458282f7d9d58d80fce&chksm=9b62091eac1580083983088a0fcde669f1411fb026879693940baf915d5b085086b54ce7c1cd&scene=21#wechat_redirect)

[JAVA 并发编程（二）线程池之 ThreadPoolExecutor](http://mp.weixin.qq.com/s?__biz=MzAwOTIwMDEyNw==&mid=2247483785&idx=1&sn=9cae4ca6f602421e9ac3e848813e76fb&chksm=9b620919ac15800f288eb9494ce5c41745d5175c7f6ddfb45a4d503255359da5cbf36d85a218&scene=21#wechat_redirect)

[java 并发编程（一）为什么需要线程，java 如何创建多线程？](http://mp.weixin.qq.com/s?__biz=MzAwOTIwMDEyNw==&mid=2247483772&idx=1&sn=23b43f506e5a0ffeca539d11f59562f5&chksm=9b6209ecac1580faaeaaf70b5c01f19958a4333c5534e73f305180bf31689f5ad9dfd59b4b67&scene=21#wechat_redirect)

![](https://mmbiz.qpic.cn/sz_mmbiz_png/nYhc8O2Qc7iaZgibZT2bibS4AsjttJx1uvenrFSvZuIaIJau6mhdtmG4SBPDQB0qXrGnibMTL5C4rICs7fI05Cr75Q/640?wx_fmt=png)

关注 "火龙果编程"，早日成为编程大神

最近，整理一份学习笔记《Spring boot 核心技术》，覆盖了 Spring boot 的方方面面，特别适合 java 新手查漏补缺

获取方式：点 “在看”，关注公众号并回复 spring 领取，更多内容陆续奉上。