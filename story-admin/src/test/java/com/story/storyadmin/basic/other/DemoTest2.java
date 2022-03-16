package com.story.storyadmin.basic.other;

import com.sun.org.apache.xpath.internal.operations.Bool;
import sun.rmi.server.InactiveGroupException;

import java.lang.Integer;  //引入包装类的包
/**
 * @author: lipan
 * @date: 2019-06-07
 * @description:
 */
public class DemoTest2 {

    public static void main(String[] args) {

        //包装类型Integer
        Integer n = new Integer(99);
        int i= n.intValue();  //将Integer转换为int类型

        //int Integer 和String的相互转换
        int i1 = 100;

        Integer n1 = new Integer(i1);
        Integer n2 = Integer.valueOf(i1);
        Integer n3 = Integer.valueOf("100"); //String 转Integer

        int x1 = n1.intValue();     //Integer 转int
        int x2 = Integer.parseInt("100");//String 转int

        String s = n1.toString(); //Integer 转String

        //Warning  特别注意Integer.getInteger(String)是从系统环境中读取系统变量
        //Integer prop = Integer.getInteger("cpus");

        Integer n4 = 99; //自动装箱：这里编译器自动在int和Integer之间转型 in -> Integer 即自动调用了Integer.valueOf(99)
        int i4 = n4;  //自动拆箱: 这里编译器自动在Integer和int之间转型 Integer -> int 即自动调用了n4.intValue();

        Integer I = i4 + n4;  //实际省略执行的操作：Integer I = Integer.valueOf(i4+n4.intValue());

        Object[] arr = new Object[1];
        arr[0] = 999; //Integer.valueOf(999); 自动装箱

        //注意！ 自动装箱和自动拆箱只发生在编译阶段 装箱和拆箱会影响执行效率 编译后的class代码是严格区分基本类型和引用类型的
        Integer x = null;
        int y = x; //NullPointerException 这段代码Integer如果为null,可能会报异常

        //包装类型定义了一些有用的静态变量
        Boolean t = Boolean.TRUE;
        Boolean f = Boolean.FALSE;
        int max = Integer.MAX_VALUE; //2147483647
        int min = Integer.MIN_VALUE; //-2147483648
        int sizeOfLong = Long.SIZE; //64(bits)
        int bytesOfLong = Long.BYTES; //8(bits)

        //整数和浮点数包装类型都继承自Number 可以通过Number 来在三种数据类型之间转换

//        Object
//           |
//        Number
//           |
//    Byte  Integer Float

        Number num = new Integer(999);
        byte b = num.byteValue();
        int in = num.intValue();
        long ln = num.longValue();
        float fl = num.floatValue();
        double d = num.doubleValue();

        //测试
        Integer n5 = 5 + Integer.valueOf(10);
        Number m = n5;
        System.out.println(m.floatValue());  //15.0




    }
}
