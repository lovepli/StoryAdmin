package com.story.storyadmin.设计模式.单例模式.demo;

import java.lang.reflect.Constructor;

/**
 * @author: lipan
 * @date: 2021/8/23
 * @description: 静态内部类
 * 利用了内部类的特性，在JVM底层，能完美的规避了线程安全的问题，这种方式也是目前很多项目里喜欢使用的方式。
 *
 * 但是，还是会存在潜在的风险，什么风险呢？
 *
 * 可以使用 反射 暴力的串改，同样也会出现创建多个实例：
 */
public class LazyStaticSingleton {


    private LazyStaticSingleton() {
        // 在构造函数中加了判断，是为了防止被反射创建实例
        if (LazyHolder.LAZY_STATIC_SINGLETON != null) {
            throw new RuntimeException("该构造方法禁止获取");
        }
    }

    //内部类会在调用的时候才加载，并只加载一次。利用这个特性可以实现 单例模式。
    public static LazyStaticSingleton getInstance() {
        return LazyHolder.LAZY_STATIC_SINGLETON;
    }

    //需要等到外部方法调用是猜执行
    //巧用内部类的特性
    //JVM底层执行，完美的规避了线程安全的问题
    private static class LazyHolder {
        private static final LazyStaticSingleton LAZY_STATIC_SINGLETON = new LazyStaticSingleton();
    }


    //反射代码实现如下：
    //这段代码运行结果为false。
    //
    //所以，上面说的双重检查锁的方式，通过反射，还是会存在潜在的风险。怎么办呢？
    //
    //在《Effect java 》这本书中，作者推荐使用枚举来实现单例模式，因为枚举不能被反射。
    public static void main(String[] args) {
        try {
            Class<?> clazz = LazyStaticSingleton.class;
            Constructor constructor = clazz.getDeclaredConstructor(null);
            //强行访问
            constructor.setAccessible(true);
            Object object = constructor.newInstance();

            Object object1 = LazyStaticSingleton.getInstance();

            System.out.println(object == object1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
