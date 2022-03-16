package com.story.storyadmin.设计模式.单例模式.demo;

/**
 * @author: lipan
 * @date: 2021/8/23
 * @description:
 * 尽管相比前面的版本，确实改进了很多，但依然有同步锁，还是会影响性能问题。于是，又进行优化为静态内部类方式
 *
 * (1)加了volatile，为了防止指令重排，如果不加，很可能第一个线程没有完全将其实例化完，第二个线程获取到的示例就会有问题。
 * （2）在构造函数中加了判断，是为了防止被反射创建实例。
 * （3）加了synchronized，为了全程安全，保证只创建一个实例。
 */
public class LazyDoubleCheckSingleton2 {
    //使用volatile修饰
    private volatile static LazyDoubleCheckSingleton2 lazyDoubleCheckSingleton = null;  //（1）

    private LazyDoubleCheckSingleton2() {
        if (lazyDoubleCheckSingleton != null) {
            throw new RuntimeException("该构造方法禁止获取");
        }
    }  //（2）

    public static LazyDoubleCheckSingleton2 getInstance() {
        if (lazyDoubleCheckSingleton == null) {
            synchronized (LazyDoubleCheckSingleton2.class) {
                if (lazyDoubleCheckSingleton == null) {
                    lazyDoubleCheckSingleton = new LazyDoubleCheckSingleton2();
                }
            }
        } //（3）
        return lazyDoubleCheckSingleton;
    }
}
