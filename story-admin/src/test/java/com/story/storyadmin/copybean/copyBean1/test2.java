package com.story.storyadmin.copybean.copyBean1;


/**
 * @author: lipan
 * @date: 2020-03-04
 * @description:
 *
 *     如何实现对象克隆？
 *     1). 实现 Cloneable 接口并重写 Object 类中的 clone()方法；
 *     2). 实现 Serializable 接口，通过对象的序列化和反序列化实现克隆，可以实现真正的深度克隆.
 *     3). 实现 Serializable 接口， 調用BeanUtils.copyProperties()的方法,TODO 缺点好像是不能复制List<User>
 *
 *     注意：基于序列化和反序列化实现的克隆不仅仅是深度克隆，更重要的是通过泛型限定，可以检查出要克隆的对
 *     象是否支持序列化，这项检查是编译器完成的，不是在运行时抛出异常，这种是方案明显优于使用 Object 类的 clone
 *     方法克隆对象。让问题在编译的时候暴露出来总是好过把问题留到运行时。
 *
 * 深拷贝的实现方式1 :实现序列化接口
 */
public class test2 {

    public static void main(String[] args) {

        DemoInternal2 di=new DemoInternal2();
        di.setInternalName("66");
        di.setInternalValue("77");

        Demo2 d1=new Demo2();
        d1.setName("哈哈");
        d1.setValue("呵呵");
        d1.setDemoInternal2(di);

        Demo2 d2= CloneUtils.clone(d1);  //TODO 深拷贝的实现
        Demo2 d3=d1;
        System.out.println(d1==d2);//false  //TODO 两个对象
        System.out.println(d1==d3);//true   //TODO 同一个对象
        System.out.println(d1);//Demo{name='哈哈', value='呵呵', demoInternal=DemoInternal{internalName='66', internalValue='77'}}
        System.out.println(d2);//Demo{name='哈哈', value='呵呵', demoInternal=DemoInternal{internalName='66', internalValue='77'}}
        System.out.println(d3);//Demo{name='哈哈', value='呵呵', demoInternal=DemoInternal{internalName='66', internalValue='77'}}


        d2.setName("hhh");
        d2.setValue("hehehe");  //TODO 结论：改变克隆对象属性值，原对象属性值不变
        System.out.println(d1);//Demo{name='哈哈', value='呵呵', demoInternal=DemoInternal{internalName='66', internalValue='77'}}
        System.out.println(d2);//Demo{name='hhh', value='hehehe', demoInternal=DemoInternal{internalName='66', internalValue='77'}}

        d1.setName("ddddd");
        d1.setValue("bbbbb");   //TODO 结论：改变原对象属性值,克隆对象属性值不变
        System.out.println(d1);//Demo{name='ddddd', value='bbbbb', demoInternal=DemoInternal{internalName='66', internalValue='77'}}
        System.out.println(d2);//Demo{name='hhh', value='hehehe', demoInternal=DemoInternal{internalName='66', internalValue='77'}}
        System.out.println(d3);//Demo{name='ddddd', value='bbbbb', Demo

    }
}
