package com.story.storyadmin.manyThread.JUC.CAS.atomic.atomicReferenceDemo;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: lipan
 * @date: 2021年08月27日 10:33 上午
 * @description:
 */
public class AtomicReferenceTest {

    //赋值
    public static void fun(){
        //1、使用 null 初始值创建新的 AtomicReference。
        AtomicReference<SimpleObject> atomicReference = new AtomicReference<>();
        atomicReference.set(new SimpleObject("张三" , 10));
        SimpleObject simpleObject = atomicReference.get();
        System.out.println("simpleObject  Value: " + simpleObject.toString());
    }

    //获取值
    public static void fun2(){
        //2、使用给定的初始值创建新的 AtomicReference。
        AtomicReference<SimpleObject> atomicReference1 = new AtomicReference<>(new SimpleObject("张三",20));
        SimpleObject simpleObject1 = atomicReference1.get();
        System.out.println("simpleObject  Value: " + simpleObject1.toString());
    }

    //比较赋值
    public static void fun3(){
        //3、如果当前值 == 预期值，则以原子方式将该值设置为给定的更新值。
        SimpleObject test = new SimpleObject("张三" , 30);
        AtomicReference<SimpleObject> atomicReference2 = new AtomicReference<>(test);
        //compareAndSet()就是调用Unsafe的cas操作,传入对象,expect值,偏移地址,需要更新的值,即可,如果更新成功,返回true,如果失败,返回false
        Boolean bool = atomicReference2.compareAndSet(test, new SimpleObject("李四", 40));
        System.out.println("simpleObject  Value: " + bool);

    }

    public static void fun4(){
        //4、以原子方式设置为给定值，并返回旧值，先获取当前对象，在设置新的对象
        SimpleObject test1 = new SimpleObject("张三" , 50);
        AtomicReference<SimpleObject> atomicReference3 = new AtomicReference<>(test1);
        SimpleObject simpleObject2 = atomicReference3.getAndSet(new SimpleObject("李四",50));
        SimpleObject simpleObject3 = atomicReference3.get();
        System.out.println("simpleObject  Value: " + simpleObject2.toString());
        System.out.println("simpleObject  Value: " + simpleObject3.toString());
    }

    //使用 AtomicReference 初始化，并赋值 https://www.cnblogs.com/fhblikesky/p/13692643.html
    // 解释说明：
    //atomicReference的初始值是user1，所以调用compareAndSet(user1, user2)，由于user1==user1，所以会把user2赋给atomicReference。此时值为“李四”
    //第二次调用atomicReference.compareAndSet(user1, user3)，由于user2 != user1，所以set失败。atomicReference仍然为“李四”
    public static void fun5() {
        SimpleObject user1 = new SimpleObject("张三", 23);
        SimpleObject user2 = new SimpleObject("李四", 25);
        SimpleObject user3 = new SimpleObject("王五", 20);

        //初始化为 user1
        AtomicReference<SimpleObject> atomicReference = new AtomicReference<>();
        atomicReference.set(user1);

        //把 user2 赋给 atomicReference
        atomicReference.compareAndSet(user1, user2);
        System.out.println(atomicReference.get());

        //把 user3 赋给 atomicReference
        atomicReference.compareAndSet(user1, user3);
        System.out.println(atomicReference.get());
    }

    public static void main(String[] args) {
       // fun();
       // fun2();
       // fun3();
       // fun4();
        fun5();
    }
}
