> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s/J4zIML-l1KW0f2YBAbUlXA)

公众号

1. Java 的线程池
------------

### ① 合理使用线程池的好处

*   **Java 的线程池**是**运用场景最多的并发框架**，**几乎所有需要异步或者并发执行任务的程序**都可以**使用线程池**。

*   合理使用线程池能带来的好处：


1.  **降低资源消耗。** 通过`重复利用已经创建的线程`**降低**`线程创建的和销毁`造成的消耗。例如，工作线程 Woker 会无线循环获取阻塞队列中的任务来执行。

2.  **提高响应速度。** 当任务到达时，`任务可以不需要等到线程创建就能立即执行`。

3.  **提高线程的可管理性。** 线程是稀缺资源，Java 的线程池可以对线程资源进行`统一分配`、`调优`和`监控`。


### ② 线程池的工作流程

*   一个新的任务到线程池时，线程池的处理流程如下：1.`线程池判断核心线程池里的线程是否都在执行任务。` 如果不是，`创建一个新的工作线程来执行任务`。如果核心线程池里的线程都在执行任务，则进入下个流程。


1.  `线程池判断阻塞队列是否已满。` 如果阻塞队列没有满，`则将新提交的任务存储在阻塞队列中`。如果阻塞队列已满，则进入下个流程。

2.  `线程池判断线程池里的线程是否都处于工作状态。` 如果没有，则创建一个新的工作线程来执行任务。如果已满，则交给饱和策略来处理这个任务。![](https://mmbiz.qpic.cn/mmbiz_png/1QxwhpDy7ia0RZCcSJicmkK06iaGicPFicNPs6AhlPkaXzhaAWvwOs7IP3ia789bmm48OtibhibTXYpia4bJsoVSD0fjdjA/640?wx_fmt=png)


*   线程池的核心实现类是`ThreadPoolExecutor类`，用来执行提交的任务。因此，任务提交到线程池时，具体的处理流程是由`ThreadPoolExecutor类`的 **execute() 方法**去完成的。![](https://mmbiz.qpic.cn/mmbiz_png/1QxwhpDy7ia0RZCcSJicmkK06iaGicPFicNPsszUYjmK5aicVtEIbXDfRA2SCftKX8bv06pqjq597ibS1kk0nasnmmBYg/640?wx_fmt=png)


1.  如果当前运行的线程少于`corePoolSize`，则创建新的工作线程来执行任务（**执行这一步骤需要获取全局锁**）。

2.  如果当前运行的线程大于或等于`corePoolSize`，而且`BlockingQueue`未满，则将任务加入到`BlockingQueue`中。

3.  如果`BlockingQueue`已满，而且当前运行的线程小于`maximumPoolSize`，则创建新的工作线程来执行任务（**执行这一步骤需要获取全局锁**）。

4.  如果当前运行的线程大于或等于`maximumPoolSize`，`任务将被拒绝`，并调用`RejectExecutionHandler.rejectExecution()方法`。即**调用饱和策略对任务进行处理。**


*   **工作线程（Worker)：** 线程池在创建线程时，会将线程封装成**工作线程 Woker**。Woker 在执行完任务后，不是立即销毁而是`循环获取阻塞队列里的任务来执行`。


### ③ 线程池的创建（7 个参数）

*   可以通过`ThreadPoolExecutor`来创建一个线程池：


```
new ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, 
 TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler)
```

*   **corePoolSize（线程池的基本大小）：**


1.  提交一个任务到线程池时，线程池会创建一个新的线程来执行任务。**注意：** 即使有`空闲的基本线程`能执行该任务，也会创建新的线程。

2.  如果线程池中的线程数已经大于或等于`corePoolSize`，则不会创建新的线程。

3.  如果调用了线程池的`prestartAllCoreThreads()方法`，线程池会`提前创建并启动所有基本线程`。


*   **maximumPoolSize（线程池的最大数量）：** 线程池允许创建的最大线程数。


1.  阻塞队列已满，线程数小于`maximumPoolSize`便可以创建新的线程执行任务。

2.  如果**使用无界的阻塞队列**，`该参数没有什么效果`。


*   **workQueue（工作队列）：** 用于保存等待执行的任务的阻塞队列。


1.  `ArrayBlockingQueue：` 基于数组结构的**有界阻塞队列**，按 **FIFO（先进先出）原则**对任务进行排序。使用该队列，线程池中能创建的最大线程数为`maximumPoolSize`。

2.  `LinkedBlockingQueue：` 基于链表结构的**无界阻塞队列**，按 **FIFO（先进先出）原则**对任务进行排序，吞吐量高于`ArrayBlockingQueue`。使用该队列，线程池中能创建的最大线程数为`corePoolSize`。**静态工厂方法** `Executor.newFixedThreadPool()`使用了这个队列。

3.  `SynchronousQueue：` 一个**不存储元素**的阻塞队列。`添加任务的操作必须等到另一个线程的移除操作`，`否则添加操作一直处于阻塞状态`。**静态工厂方法** `Executor.newCachedThreadPool()`使用了这个队列。

4.  `PriorityBlokingQueue：` 一个**支持优先级**的**无界阻塞队列**。使用该队列，线程池中能创建的最大线程数为`corePoolSize`。


*   **keepAliveTime（线程活动保持时间）：** 线程池的`工作线程空闲后`，`保持存活的时间`。如果`任务多而且任务的执行时间比较短`，可以**调大**`keepAliveTime`，提高线程的利用率。

*   **unit（线程活动保持时间的单位）：** 可选单位有`DAYS`、`HOURS`、`MINUTES`、`毫秒`、`微秒`、纳`秒`。

*   **handler（饱和策略，或者又称拒绝策略）：** 当**队列和线程池都满了**，即**线程池饱和了**，必须采取一种策略处理提交的新任务。


1.  `AbortPolicy：` 无法处理新任务时，`直接抛出异常`，这是**默认策略**。

2.  `CallerRunsPolicy：`用调用者所在的线程来执行任务。

3.  `DiscardOldestPolicy：`丢弃阻塞队列中**最靠前**的一个任务，并执行当前任务。

4.  `DiscardPolicy：` 直接丢弃任务。


*   **threadFactory：** 构建线程的工厂类

*   **总结：**


1.  常用的 5 个，核心池、最大池、空闲时间、时间的单位、阻塞队列；另外两个：拒绝策略、线程工厂类

2.  常见线程池的创建参数如下。**PS:** `CachedThreadPool`核心池为 0，最大池为`Integer.MAX_VALUE`，相当于**只使用了最大池**；其他线程池，核心池与最大池一样大，因此相当于**只用了核心池**。


```
FixedThredPool: new ThreadExcutor(n, n, 0L, ms, new LinkedBlockingQueue<Runable>()
SingleThreadExecutor: new ThreadExcutor(1, 1, 0L, ms, new LinkedBlockingQueue<Runable>())
CachedTheadPool: new ThreadExcutor(0, max_valuem, 60L, s, new SynchronousQueue<Runnable>());
ScheduledThreadPoolExcutor: ScheduledThreadPool, SingleThreadScheduledExecutor.
```

1.  如果使用的阻塞队列为**无界队列**，则**永远不会调用拒绝策略**，因为再多的任务都可以放在队列中。

2.  `SynchronousQueue`是**不存储任务**的，新的任务要么立即被已有线程执行，要么创建新的线程执行。


### ④ 向线程池提交任务

*   使用`ThreadPoolEXecutor.execute()`方法来提交任务：


```
public void execute(Runnable command) {
    // command为null，抛出NullPointerException
    if (command == null)
        throw new NullPointerException();      
    int c = ctl.get();
    // 线程池中的线程数小于corePoolSize，创建新的线程
    if (workerCountOf(c) < corePoolSize) {
        if (addWorker(command, true))// 创建工作线程
            return;
        c = ctl.get();
    }
    // 将任务添加到阻塞队列，如果
    if (isRunning(c) && workQueue.offer(command)) {
        int recheck = ctl.get();
        if (! isRunning(recheck) && remove(command))
            reject(command);
        else if (workerCountOf(recheck) == 0)
            addWorker(null, false);
    }// 阻塞队列已满，尝试创建新的线程，如果超过maximumPoolSize，执行handler.rejectExecution()
    else if (!addWorker(command, false))
        reject(command);
}
```

### ⑤ 线程池的五种运行状态

*   **RUNNING ：** 该状态的线程池**既能接受新提交的任务**，**又能处理阻塞队列中任务**。

*   **SHUTDOWN：** 该状态的线程池**不能接收新提交的任务**，但是**能处理阻塞队列中的任务**。（政府服务大厅不在允许群众拿号了，处理完手头的和排队的政务就下班。）


1.  处于 `RUNNING 状态`时，调用 `shutdown()方法`会使线程池进入到该状态。

2.  **注意：** `finalize() 方法`在执行过程中也会**隐式调用**`shutdown()方法`。


*   **STOP：** 该状态的线程池**不接受新提交的任务**，也**不处理在阻塞队列中的任务**，**还会中断正在执行的任务**。（政府服务大厅不再进行服务了，拿号、排队、以及手头工作都停止了。）


1.  在线程池处于 `RUNNING 或 SHUTDOWN 状态`时，调用 `shutdownNow() 方法`会使线程池进入到该状态；


*   **TIDYING：** 如果`所有的任务都已终止`，`workerCount (有效线程数)=0` 。


1.  线程池进入该状态后会调用 `terminated() 钩子方法`进入`TERMINATED 状态`。


*   **TERMINATED：** 在`terminated()钩子方法`执行完后进入该状态，**默认`terminated()钩子方法`中什么也没有做**。![](https://mmbiz.qpic.cn/mmbiz_png/1QxwhpDy7ia0RZCcSJicmkK06iaGicPFicNPsw8nFeqbUwqkxibtVdajyMlPbNfmvT50KmQZzAKqicE19sQ0W9sVTAFQw/640?wx_fmt=png)


### ⑥ 线程池的关闭（`shutdown或者shutdownNow方法`）

*   可以通过调用线程池的`shutdown或者shutdownNow方法`来关闭线程池：遍历线程池中工作线程，逐个调用`interrupt方法`来**中断线程**。

*   **shutdown 方法与 shutdownNow 的特点：**


1.  `shutdown方法`将线程池的状态设置为`SHUTDOWN状态`，**只会中断空闲的工作线程**。

2.  `shutdownNow方法`将线程池的状态设置为`STOP状态`，**会中断所有工作线程**，不管工作线程是否空闲。

3.  调用两者中任何一种方法，都会使`isShutdown方法`的返回值为 true；`线程池中所有的任务都关闭后`，`isTerminated方法`的返回值为 true。

4.  通常使用`shutdown方法`关闭线程池，如果不要求任务一定要执行完，则可以调用`shutdownNow方法`。


2. java 线程池的调优以及监控
------------------

### ① 线程池的调优（线程池的合理配置）

*   先从以下几个角度分析任务的特性：


1.  **任务的性质：** `CPU 密集型任务`、`IO 密集型任务`和`混合型任务`。

2.  **任务的优先级：** 高、中、低。

3.  **任务的执行时间：** 长、中、短。

4.  **任务的依赖性：** `是否依赖其他系统资源`，如`数据库连接`。


*   **任务性质不同的任务可以用不同规模的线程池分开处理。** 可以通过 `Runtime.getRuntime().availableProcessors()` 方法获得当前设备的 CPU 个数。


1.  **CPU 密集型任务**配置`尽可能小的线程`，如配置 N c p u + 1 N_{cpu}+1_N**c**p**u_+1 个线程的线程池。

2.  **IO 密集型任务**则由于线程并不是一直在执行任务，则`配置尽可能多的线程`，如 2 ∗ N c p u 2_N_{cpu}2∗_N**c**p**u*。

3.  **混合型任务**，如果可以拆分，则`将其拆分成一个 CPU 密集型任务和一个 IO 密集型任务`。只要这`两个任务执行的时间相差不是太大`，那么`分解后执行的吞吐率要高于串行执行的吞吐率`；如果这两个任务执行时间相差太大，则没必要进行分解。


*   **优先级不同的任务**可以使用优先级队列 `PriorityBlockingQueue` 来处理，它可以让优先级高的任务先得到执行。但是，如果`一直有高优先级的任务加入到阻塞队列中`，那么`低优先级的任务可能永远不能执行`。

*   **执行时间不同的任务**可以交给`不同规模的线程池`来处理，或者`也可以使用优先级队列`，让`执行时间短的任务先执行`。

*   **依赖数据库连接池的任务**，因为线程提交 SQL 后需要等待数据库返回结果，`线程数应该设置得较大`，这样才能更好的利用 CPU。

*   **建议使用有界队列**，有界队列能`增加系统的稳定性和预警能力`。可以根据需要设大一点，比如几千。`使用无界队列`，线程池的队列就会越来越大，**有可能会撑满内存，导致整个系统不可用**。


### ② 线程池的监控

*   可以通过线程池提供的参数读线程池进行监控，有以下属性可以使用：


1.  `taskCount：`线程池需要执行的任务数量，包括已经执行完的、未执行的和正在执行的。

2.  `completedTaskCount：`线程池在运行过程中**已完成的任务数量**，`completedTaskCount <= taskCount`。

3.  `largestPoolSize：`线程池**曾经创建过的最大线程数量**，`通过这个数据可以知道线程池是否满过`。**如等于线程池的最大大小**，则表示`线程池曾经满了`。

4.  `getPoolSize:` 线程池的线程数量。如果`线程池不销毁`的话，`池里的线程不会自动销毁`，所以**线程池的线程数量只增不减**。

5.  `getActiveCount：`获取**活动的**线程数。


*   通过**继承线程池**并**重写**线程池的 `beforeExecute`，`afterExecute` 和 `terminated` 方法，我们可以在`任务执行前`，`执行后`和`线程池关闭前`干一些事情。

*   如监控任务的`平均执行时间`，`最大执行时间`和`最小执行时间`等。**这几个方法在线程池里是空方法**，如：


```
protected void beforeExecute(Thread t, Runnable r) { }
```

3. Java 线程池的常见问题
----------------

**1. 讲讲 Java 的线程池**

*   **基础讲解：**


1.  以`ThreadPoolExecutor`为切入点，讲解`excute()方法`中所体现的`Java线程池运行流程`。

2.  工作线程 Worker，它的循环工作特点

3.  如何新建线程池：7 个参数（重点在阻塞队列和饱和策略）


*   **进阶：**


1.  线程池`五个状态的特点`以及`如何进行状态之间的切换`：`running`、`shutdown`、`stop`、`tidying`、`terminated`。

2.  如何关闭线程：`shutdown方法`和`shutdownNow方法`的特点

3.  线程池的调优（`针对任务的不同特性` + `建议使用有界队列`）

4.  线程池的`监控参数`以及`可以重写的方法`。


* * *

*   两种主要的线程池类型：普通的线程池`ThreadPoolExecutor`，支持延迟或周期性执行的任务的线程池`ScheduledThreadPoolExcutor`。

*   讲解`ThreadPoolExcutor`中 5 个常用参数 + 2 个不常用参数，包含的三种线程池：创建时的参数、运行的流程、各自适合的场景。

*   讲解`ScheduledThreadPoolExecutor`的阻塞队列的原理、如何更改任务的 time。

*   提供了五种定义好的线程池，都可以通过`Executors`工具类去调用，比如`Executors.newFixedThreadPool(12)`


**2. 具体的场景，如果 corePoolSize 为 x，maximumPoolSize 为 y，阻塞队列为 z，第 w 个任务进来如何分配？**

**3. 线程池如何进行调优？**

*   线程池的调优（`针对任务的不同特性` + `建议使用有界队列`）


**4. 线程池中的核心参数，超过核心 size 怎么处理，队列满怎么处理，拒绝策略有哪些？（比较具体）**

来源 | blog.csdn.net/u014454538/article/details/96910729