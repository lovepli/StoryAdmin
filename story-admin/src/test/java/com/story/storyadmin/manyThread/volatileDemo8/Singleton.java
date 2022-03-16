package com.story.storyadmin.manyThread.volatileDemo8;

/**
 * @author: 59688
 * @date: 2021/7/28
 * @description:
 */
public class Singleton {

    private volatile static Singleton uniqueInstance;

    private Singleton() {
    }

    public static Singleton getUniqueInstance() {
        //先判断对象是否已经实例过，没有实例化过才进入加锁代码
        if (uniqueInstance == null) {
            //类对象加锁
            synchronized (Singleton.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }
}

/**
 * 2.1. 讲一下Java内存模型
 * 在 JDK1.2 之前，Java的内存模型实现总是从主存（即共享内存）读取变量，是不需要进行特别的注意的。而在当前的 Java 内存模型下，
 * 线程可以把变量保存本地内存（比如机器的寄存器）中，而不是直接在主存中进行读写。这就可能造成一个线程在主存中修改了一个变量的值，
 * 而另外一个线程还继续使用它在寄存器中的变量值的拷贝，造成数据的不一致。
 *
 * 要解决这个问题，就需要把变量声明为volatile，这就指示 JVM，这个变量是不稳定的，每次使用它都到主存中进行读取。
 * 说白了， volatile 关键字的主要作用就是保证变量的可见性然后还有一个作用是防止指令重排序。
 */


/**
 * 解释说明：
 * 另外，需要注意 uniqueInstance 采用 volatile 关键字修饰也是很有必要。
 *
 * uniqueInstance 采用 volatile 关键字修饰也是很有必要的， uniqueInstance = new Singleton(); 这段代码其实是分为三步执行：
 *
 * 1、为 uniqueInstance 分配内存空间
 * 2、初始化 uniqueInstance
 * 3、将 uniqueInstance 指向分配的内存地址
 * 但是由于 JVM 具有指令重排的特性，执行顺序有可能变成 1->3->2。指令重排在单线程环境下不会出现问题，但是在多线程环境下会导致一个线程获得还没有初始化的实例。
 * 例如，线程 T1 执行了 1 和 3，此时 T2 调用 getUniqueInstance() 后发现 uniqueInstance 不为空，因此返回 uniqueInstance，但此时 uniqueInstance 还未被初始化。
 *
 * 使用 volatile 可以禁止 JVM 的指令重排，保证在多线程环境下也能正常运行。
 * volatile 关键字的主要作用就是保证变量的可见性然后还有一个作用是防止指令重排序。
 */
