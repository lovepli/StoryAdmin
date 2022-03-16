package com.story.storyadmin.web.requestParamTestController;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: 59688
 * @date: 2021/8/5
 * @description: SpringMVC：如何保证Controller的并发安全 https://mp.weixin.qq.com/s/5CjASWDr4YqE4wCI6ugQrg
 *
 * 根据Tomcat官网中的介绍，对于一个浏览器请求，tomcat会指定一个处理线程，或是在线程池中选取空闲的，或者新建一个线程。
 * 在Tomcat容器中，每个servlet是单例的。在SpringMVC中，Controller 默认也是单例。 采用单例模式的最大好处，就是可以在高并发场景下极大地节省内存资源，提高服务抗压能力。
 * 单例模式容易出现的问题是：在Controller中定义的实例变量，在多个请求并发时会出现竞争访问，Controller中的实例变量不是线程安全的。
 *
 * 正因为Controller默认是单例，所以不是线程安全的。如果用SpringMVC 的 Controller时，尽量不在 Controller中使用实例变量，否则会出现线程不安全性的情况，导致数据逻辑混乱。
 *
 * 举一个简单的例子，在一个Controller中定义一个非静态成员变量 num 。通过Controller成员方法来对 num 增加。
 */
//@Controller
public class TestController {

    /**Controller不是线程安全的 例子1 */
    //private int num = 0;
    //
    //@RequestMapping("/addNum")
    //public void addNum() {
    //    System.out.println(++num);
    //}

    /**
     * 在本地运行后：
     * 首先访问 http:// localhost:8080 / addNum，得到的答案是1；
     * 再次访问 http:// localhost:8080 / addNum，得到的答案是 2。
     * 两次访问得到的结果不同，num已经被修改，并不是我们希望的结果，接口的幂等性被破坏。
     * 从这个例子可以看出，所有的请求访问同一个Controller实例，Controller的私有成员变量就是线程共用的。
     * 某个请求对应的线程如果修改了这个变量，那么在别的请求中也可以读到这个变量修改后的的值。
     */
}


/**
 * Controller并发安全的解决办法
 * 如果要保证Controller的线程安全，有以下解决办法：
 *
 * 尽量不要在 Controller 中定义成员变量 ；
 * 如果必须要定义一个非静态成员变量，那么可以通过注解 @Scope(“prototype”) ，将Controller设置为多例模式。
 */
//@Controller
//@Scope(value="prototype")
class TestController2 {

    private int num = 0;

    @RequestMapping("/addNum")
    public void addNum() {
        System.out.println(++num);
    }
    /**
     * Scope属性是用来声明IOC容器中的对象（Bean ）允许存在的限定场景，或者说是对象的存活空间。在对象进入相应的使用场景之前，IOC容器会生成并装配这些对象；当该对象不再处于这些使用场景的限定时，容器通常会销毁这些对象。
     * Controller也是一个Bean，默认的 Scope 属性为Singleton ，也就是单例模式。如果Bean的 Scope 属性设置为 prototype 的话，容器在接受到该类型对象的请求时，每次都会重新生成一个新的对象给请求方。
     */
}

/**
 * 解决方式二：Controller 中使用 ThreadLocal 变量。 每一个线程都有一个变量的副本。
 * 以下代码运行以后，每次请求 http:// localhost:8080 / addNum , 得到的结果都是1。
 *
 * 更严格的做法是用AtomicInteger类型定义成员变量，对于成员变量的操作使用AtomicInteger的自增方法完成。
 * 总的来说，还是尽量不要在 Controller 中定义成员变量为好。
 */
//@Controller
class TestController3 {

    private int num = 0;

    private final ThreadLocal <Integer> uniqueNum =
            new ThreadLocal <Integer> () {
                @Override
                protected Integer initialValue() {
                    return num;
                }
            };

    @RequestMapping("/addNum")
    public void addNum() {
        int unum = uniqueNum.get();
        uniqueNum.set(++unum);
        System.out.println(uniqueNum.get());
    }
}