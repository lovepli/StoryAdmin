> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s/aj-Mm4PvMfD-Z8hPiyLBTA)

一、简介
----

Java 的类库有很多类，而且在不断的扩展，我们不需要把每一个类都掌握，只需要学习 Java 的核心类即可

那么，Java 的核心类有哪些呢？

```
提供了通过输出流，对象序列以及文件系统实现的系统输入、输出
```

包名以 java 开始的包是 Java 核心包。包名以 javac 开始的包是 Java 扩展包

二、数字相关的类
--------

**Java 数字类**

• 整型：Short,Int,Long• 浮点型：Float,Double• 大数类：BigInteger(大整数),BigDecimal(大浮点数)• 随机数类：Random• 工具类：Math

**详细介绍：**

1、整型

> short ，16 位，2 个字节，数值范围`最小`，默认值 0

> int ，32 位，4 个字节，默认值为 0

> long ，64 位，8 个字节，数值范围`最大`，默认值`0L`

2、浮点型

> float ，单精度，32 位，4 个字节，默认值为`0.0f`，`f`不可省略

> double ，双精度，32 位，4 个字节，默认值为 0.0d，`d`可省略

float 和 double 都不能表示很精确的数字 例如：![](https://mmbiz.qpic.cn/sz_mmbiz_png/rVVgBxVy9rgNOBsDsiacFicWMu92MLsp5h5WhJfCyP0pXgTHdsVweIKkVxP3vV9peVRG23Nz7VPvcH6hgEIbxh9g/640?wx_fmt=png)

3、大数类 这是 java 类，而不是类型, 里面是有方法的

> BigInteger ，大整数类，普通的整数类型是有范围值的，而 BigInteger 支持无限大的整数运算

> BigDecimal ，大浮点类，能够表示更加精确的数字

```
//valueOf()方法将int类型的数据转换为大数，则大数可以进行计算，int类型的数据不能直接和大数进行计算
BigInteger a=BigInteger.valueOf(20);
//BigInteger不能使用算术运算符（+和*）进行计算
//如果想对BigInteger进行加运算时，如下操作
BigInteger b=BigInteger.valueOf(10);
BigInteger c=a.add(b);//此时，b为其他的BigInteger数据,c=a+b
//如果想对BigInteger进行乘运算时，如下操作
BigInteger d=c.multiply(a);//d=c*a
提示：BigDecimal是对浮点类型进行处理，其他使用的方法和BigInteger一样
举例：BigDecimal f=BigDecimal.valueOf(3.22);
注意：使用大数时，需要导入：import java.math;这个类包
```

4、Random 随机数

```
返回一个随机 int 类型数字
```

5、工具类 java.lang.Math

•abs ：绝对值函数 •log ：对数函数 •max ：最大值比较 •min ：最小值比较 •pow ：幂函数 •round ：四舍五入函数 •floor ：向下取整 •ceil ：向上取整

```
public class MathTest{
    public static void main(String[] args){
        System.out.println(Math.abs(-4));//绝对值，结果为4
        System.out.println(Math.max(-3,-7));//最大值，结果为-3
        System.out.println(Math.min(-3,-7));//最小值，结果为-7
        System.out.println(Math.pow(-4,2));//求幂,表示-4的2次方，结果为16.0
        System.out.println(Math.round(2.5));//四舍五入，最后结果为3
        System.out.println(Math.ceil(2.5));//向上取整，结果为3.0
        System.out.println(Math.floor(2.5));//向下取整，结果为2.0
    }
}
```

三、字符串相关的类
---------

字符串分为不可变字符串和可变字符串 在实际业务中，我们经常会用到它们

1、不可变字符串 **String** : 不可变，只读

<table><thead data-darkmode-bgcolor-16278328366957="rgba(56, 56, 56, 0.05)" data-darkmode-original-bgcolor-16278328366957="#fff|rgba(0, 0, 0, 0.05)" data-style="box-sizing: border-box; line-height: 1.5; background: rgba(0, 0, 0, 0.05);"><tr data-darkmode-bgcolor-16278328366957="rgba(56, 56, 56, 0.05)" data-darkmode-original-bgcolor-16278328366957="#fff|rgba(0, 0, 0, 0.05)"><td data-darkmode-bgcolor-16278328366957="rgba(56, 56, 56, 0.05)" data-darkmode-original-bgcolor-16278328366957="#fff|rgba(0, 0, 0, 0.05)" data-style="box-sizing: border-box; padding: 4px 8px; line-height: 1.5; font-size: 12.8px; border-color: rgb(223, 223, 223);">方法</td><td data-darkmode-bgcolor-16278328366957="rgba(56, 56, 56, 0.05)" data-darkmode-original-bgcolor-16278328366957="#fff|rgba(0, 0, 0, 0.05)" data-style="box-sizing: border-box; padding: 4px 8px; line-height: 1.5; font-size: 12.8px; border-color: rgb(223, 223, 223);">作用</td></tr></thead><tbody><tr><td data-style="box-sizing: border-box; padding: 4px 8px; line-height: 1.5; font-size: 12.8px; border-color: rgb(223, 223, 223);">length()</td><td data-style="box-sizing: border-box; padding: 4px 8px; line-height: 1.5; font-size: 12.8px; border-color: rgb(223, 223, 223);">返回字符串的长度</td></tr><tr><td data-style="box-sizing: border-box; padding: 4px 8px; line-height: 1.5; font-size: 12.8px; border-color: rgb(223, 223, 223);">isEmpty()</td><td data-style="box-sizing: border-box; padding: 4px 8px; line-height: 1.5; font-size: 12.8px; border-color: rgb(223, 223, 223);">返回字符串是否为空，如果为 true，则 length() 为 0</td></tr><tr><td data-style="box-sizing: border-box; padding: 4px 8px; line-height: 1.5; font-size: 12.8px; border-color: rgb(223, 223, 223);">charAt（int index）</td><td data-style="box-sizing: border-box; padding: 4px 8px; line-height: 1.5; font-size: 12.8px; border-color: rgb(223, 223, 223);">返回 char 指定索引处的值</td></tr><tr><td data-style="box-sizing: border-box; padding: 4px 8px; line-height: 1.5; font-size: 12.8px; border-color: rgb(223, 223, 223);">compareTo（String anonter）</td><td data-style="box-sizing: border-box; padding: 4px 8px; line-height: 1.5; font-size: 12.8px; border-color: rgb(223, 223, 223);">按字典顺序比较两个字符串</td></tr><tr><td data-style="box-sizing: border-box; padding: 4px 8px; line-height: 1.5; font-size: 12.8px; border-color: rgb(223, 223, 223);">concat(String str)</td><td data-style="box-sizing: border-box; padding: 4px 8px; line-height: 1.5; font-size: 12.8px; border-color: rgb(223, 223, 223);">将指定的字符串连接到该字符串的末尾</td></tr><tr><td data-style="box-sizing: border-box; padding: 4px 8px; line-height: 1.5; font-size: 12.8px; border-color: rgb(223, 223, 223);">contains(CharSequence s)</td><td data-style="box-sizing: border-box; padding: 4px 8px; line-height: 1.5; font-size: 12.8px; border-color: rgb(223, 223, 223);">当且仅当此字符串包含指定的 char 值序列时才返回 true</td></tr><tr><td data-style="box-sizing: border-box; padding: 4px 8px; line-height: 1.5; font-size: 12.8px; border-color: rgb(223, 223, 223);">indexOf(int ch)</td><td data-style="box-sizing: border-box; padding: 4px 8px; line-height: 1.5; font-size: 12.8px; border-color: rgb(223, 223, 223);">返回指定字符第一次出现的字符串内的索引</td></tr><tr><td data-style="box-sizing: border-box; padding: 4px 8px; line-height: 1.5; font-size: 12.8px; border-color: rgb(223, 223, 223);">indexOf(int ch, int fromIndex)</td><td data-style="box-sizing: border-box; padding: 4px 8px; line-height: 1.5; font-size: 12.8px; border-color: rgb(223, 223, 223);">返回指定字符第一次出现的字符串内的索引，以指定的索引开始搜索</td></tr><tr><td data-style="box-sizing: border-box; padding: 4px 8px; line-height: 1.5; font-size: 12.8px; border-color: rgb(223, 223, 223);">trim()</td><td data-style="box-sizing: border-box; padding: 4px 8px; line-height: 1.5; font-size: 12.8px; border-color: rgb(223, 223, 223);">返回一个字符串，其值为此字符串，并删除任何前导和尾随空格。</td></tr><tr><td data-style="box-sizing: border-box; padding: 4px 8px; line-height: 1.5; font-size: 12.8px; border-color: rgb(223, 223, 223);">split(String regex)</td><td data-style="box-sizing: border-box; padding: 4px 8px; line-height: 1.5; font-size: 12.8px; border-color: rgb(223, 223, 223);">将此字符串分割并用数组存储</td></tr><tr><td data-style="box-sizing: border-box; padding: 4px 8px; line-height: 1.5; font-size: 12.8px; border-color: rgb(223, 223, 223); word-break: break-all;">substring(int beginIndex, int endIndex)</td><td data-style="box-sizing: border-box; padding: 4px 8px; line-height: 1.5; font-size: 12.8px; border-color: rgb(223, 223, 223);">从开始下标到结束下标的截取</td></tr></tbody></table>

```
public class MathTest {
    public static void main(String[] args) {
            String s="12;34;56;78;9";
            String s2="12;45";
            System.out.println(s.length());//获取长度，13
            System.out.println(s.charAt(4));//从零开始，第五个位置为4
            System.out.println(s.isEmpty());//不为空，返回false
            System.out.println(s.compareTo(s2));//返回-1，表示前面的字符串小。返回0，说明相等。返回1，说明前面的字符串大
            System.out.println(s.concat(s2));//把后面的字符串，连接到前面字符串的末尾
            System.out.println(s.contains(s2));//前面字符串是否包含后面的字符串,返回flase或true
            System.out.println(s.indexOf(";"));//返回第一次出现的该字符串的下标,这里返回2
            System.out.println(s.indexOf("8", 2));//从下标为2开始找，找到则返回下标的值，否则返回-1
            System.out.println(s.trim());//去掉字符串前后的空格，中间空格不管
            String[] array=s.split(";");//字符串分割
            for(String a:array) {
                System.out.println(a);
            }
            System.out.println(s.substring(2,5));//从下标2到下标5的截取
    }
}
```

2、可变字符串

•StringBuffer（字符串的加减，同步，性能好）•StringBuilder(字符串的加减，不同步，性能更好)

在对字符串频繁的插入和删除操作时，一般都是用可变字符串提高性能

<table><thead data-darkmode-bgcolor-16278328366957="rgba(56, 56, 56, 0.05)" data-darkmode-original-bgcolor-16278328366957="#fff|rgba(0, 0, 0, 0.05)" data-style="box-sizing: border-box; line-height: 1.5; background: rgba(0, 0, 0, 0.05);"><tr data-darkmode-bgcolor-16278328366957="rgba(56, 56, 56, 0.05)" data-darkmode-original-bgcolor-16278328366957="#fff|rgba(0, 0, 0, 0.05)"><td width="162.66666666666666" data-darkmode-bgcolor-16278328366957="rgba(56, 56, 56, 0.05)" data-darkmode-original-bgcolor-16278328366957="#fff|rgba(0, 0, 0, 0.05)" data-style="box-sizing: border-box; padding: 4px 8px; line-height: 1.5; font-size: 12.8px; border-color: rgb(223, 223, 223);">方法</td><td width="338.6666666666667" data-darkmode-bgcolor-16278328366957="rgba(56, 56, 56, 0.05)" data-darkmode-original-bgcolor-16278328366957="#fff|rgba(0, 0, 0, 0.05)" data-style="box-sizing: border-box; padding: 4px 8px; line-height: 1.5; font-size: 12.8px; border-color: rgb(223, 223, 223);">作用</td></tr></thead><tbody><tr><td width="23" data-style="box-sizing: border-box; padding: 4px 8px; line-height: 1.5; font-size: 12.8px; border-color: rgb(223, 223, 223); word-break: break-all;"><span data-darkmode-color-16278328366957="rgb(221, 152, 68)" data-darkmode-original-color-16278328366957="#fff|rgb(251, 172, 77)" data-style="color: rgb(251, 172, 77);">append(String s)</span></td><td width="360.6666666666667" data-style="box-sizing: border-box; padding: 4px 8px; line-height: 1.5; font-size: 12.8px; border-color: rgb(223, 223, 223);">将指定的字符串附加到此字符序列</td></tr><tr><td width="23" data-style="box-sizing: border-box; padding: 4px 8px; line-height: 1.5; font-size: 12.8px; border-color: rgb(223, 223, 223);"><span data-darkmode-color-16278328366957="rgb(221, 152, 68)" data-darkmode-original-color-16278328366957="#fff|rgb(251, 172, 77)" data-style="color: rgb(251, 172, 77);">delete(int start, int end)</span></td><td width="360.6666666666667" data-style="box-sizing: border-box; padding: 4px 8px; line-height: 1.5; font-size: 12.8px; border-color: rgb(223, 223, 223);">删除此序列的子字符串中的字符</td></tr></tbody></table>

StringBuffer 和 StringBuilder 都是一个类，使用要实例化才能使用

```
//StringBuilder使用一样，唯一不同的是同步问题，这个涉及到多线程的知识
public class MathTest {
    public static void main(String[] args) {
            StringBuffer s=new StringBuffer();
            s.append("12");
            s.append(34);
            System.out.println(s);//最后输出结果是1234，会自动将int转为字符串存储
            System.out.println(s.delete(0, 2));//最后输出结果是34
            System.out.println(s.append("1"));//最后输出为341
    }
}
```

其他内容请翻阅 jdk 中文文档进行查阅。

这篇内容边幅有点长，留到下一篇续写了

每天进步一点点，下一篇继续 Java 常用类的学习

![](https://mmbiz.qpic.cn/sz_mmbiz_gif/rVVgBxVy9rh8zH4gNN7hKfqTrZa2nibIYWzECicYgRW6f1kmDfgw405hPZhplVCs1TGAAPIF31OeLIMH2nTTfoVg/640?wx_fmt=gif)





![](https://mmbiz.qpic.cn/sz_mmbiz_jpg/rVVgBxVy9rh8zH4gNN7hKfqTrZa2nibIYe5tFiaoMACjoK1ZciasqcvBZBBA2Bgfnfauk1AmRTicgb6ThpZAba133w/640?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/sz_mmbiz_png/rVVgBxVy9rh8zH4gNN7hKfqTrZa2nibIY7ADOartJp2PcBZfxWlL7NNOHibWTA8hhtC9LYNaA1VoenibYSVLvZPLw/640?wx_fmt=png)

扫码关注我

主动笃职，认真负责、

求实进取，互动互助。

![](https://mmbiz.qpic.cn/mmbiz_gif/FIBZec7ucCiaOJv1xaGlYbnpgUyOXeX35h4VplHTjV7EHZam8F7iccGJDGuqbMZHrrHz6ljQoEmMWI0ic2CfkLkuA/640?wx_fmt=gif)