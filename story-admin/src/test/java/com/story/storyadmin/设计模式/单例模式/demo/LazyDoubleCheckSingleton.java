package com.story.storyadmin.设计模式.单例模式.demo;

/**
 * @author: lipan
 * @date: 2021/8/23
 * @description:  双重检查
 * 这段代码中，在01行，如果不为空，就直接返回，这是第一次检查。如果为空，则进入同步代码块，02行又进行一次检查。
 */
public class LazyDoubleCheckSingleton {
    private static LazyDoubleCheckSingleton lazyDoubleCheckSingleton = null;

    private LazyDoubleCheckSingleton() {
    }

    public static LazyDoubleCheckSingleton getInstance() {
        if (lazyDoubleCheckSingleton == null) {//01
            synchronized (LazyDoubleCheckSingleton.class) {
                if (lazyDoubleCheckSingleton == null) {//02
                    lazyDoubleCheckSingleton = new LazyDoubleCheckSingleton();
                }
            }
        }
        return lazyDoubleCheckSingleton;
    }

    /**
     * 双重检查就是现实if判断、获取类对象锁、if判断。
     *
     * 上面这段代码，看似没问题，其实还是有问题的，比如：指令重排序（需要有JVM知识垫底哈）
     *
     * 指令重排是什么意思呢？
     *
     * 比如java中简单的一句
     *
     * lazyDoubleCheckSingleton = new LazyDoubleCheckSingleton();
     * 会被编译器编译成如下JVM指令：
     *
     * memory =allocate();    //1：分配对象的内存空间
     *
     * ctorInstance(memory);  //2：初始化对象
     *
     * instance =memory;     //3：设置instance指向刚分配的内存地址
     *
     * 但是这些指令顺序并非一成不变，有可能会经过JVM和CPU的优化，指令重排成下面的顺序：
     *
     * memory =allocate();    //1：分配对象的内存空间
     *
     * instance =memory;     //3：设置instance指向刚分配的内存地址
     *
     * ctorInstance(memory);  //2：初始化对象
     *
     * 为了防止指令重排序，所以，我们可以使用volatile来做文章（注意：volatile能防止指令重排序和线程可见性）。
     *
     * 于是，更好的版本就出来了。
     */

}
