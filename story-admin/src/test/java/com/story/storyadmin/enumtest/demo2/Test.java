package com.story.storyadmin.enumtest.demo2;

/**
 * @author: lipan
 * @date: 2021/8/20
 * @description:
 * Java 规范中规定，每一个枚举类型及其定义的枚举变量在 JVM 中都是唯一的，并且在枚举类型的序列化和反序列化上，Java 做了特殊的规定。在序列化的时候 Java 仅仅是将枚举对象的 name 属性输出到结果中，反序列化的时候则是通过 java.lang.Enum 的 valueOf() 方法来根据名字查找枚举对象，因此反序列化后的实例也会和之前被序列化的对象实例相同。
 *
 * 不过个人觉得想要实现单例就要将这个对象设计成枚举类型的，虽然安全可靠，但还是不优雅。
 */
public class Test {

    //我们起两个线程来测试 运行结果为True。
    //
    //由之前的反编译可知，属性INSTANCE 被声明为static的。枚举实现实例化时是线程安全。
    public static void main(String[] args) {
        EnumSingleton instance1 = EnumSingleton.INSTANCE.getInstance();
        EnumSingleton instance2 = EnumSingleton.INSTANCE.getInstance();
        System.out.println(instance1 == instance2);
    }
}
