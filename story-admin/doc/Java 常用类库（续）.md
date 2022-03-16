> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s/EzqyHNkBZZR9QfLVu_8D6Q)

上一篇我们讲到常用类库的介绍，数字相关类库和字符串相关类库，如果还没看的小伙伴可以回去看上一篇的内容：[Java 常用类](http://mp.weixin.qq.com/s?__biz=Mzg3MjY1MDMxNA==&mid=2247483927&idx=1&sn=1e0f65d85ded2a7b8e37e236055a3e4d&chksm=ceed4132f99ac8240eba8a0ee4dd5300a002bf99abf2767386076c8789c6a83ed04137ab5a86&scene=21#wechat_redirect)

本篇内容我们继续讲解 Java 常用类库中的`时间`相关类库和`格式化`相关类库

一、时间相关类
-------

时间相关类 / 类库有

> `java.util.Date`包 (已经废弃，不推荐使用，本篇不对该类进行讲解)

> `java.sql.Date`包 (数据库时间对应的类，不常用)

> `Calendar`类 (目前程序最常用，`抽象类`)

> `java.time`包 (java8 新的 API)

我们对第三第四点内容进行讲解

### 1、Calendar

> Calendar 的使用

```
//方式一，使用getInstance()方法进行实例化，实际上是实现了GregorianCalendar子类
Calendar ca=Calendar.getInstance();
//方式二，通过子类进行实例化,使用到简单工厂模式，暂时简单了解即可
Calendar ca=new GregorianCalendar();
```

Calendar 的方法

```
获取时间中每个属性的值，月份是 0-11, 所以一般使用同时要加一操作
```

```
import java.util.Calendar;
public class CalendarTest {
    public static void main(String[] args) {
        //实例化Calendar对象
        Calendar ca=Calendar.getInstance();
        //获取年
        int year=ca.get(Calendar.YEAR);
        //获取月，需要后面加1操作
        int monday = ca.get(Calendar.MONDAY)+1;
        //获取日
        int date=ca.get(Calendar.DATE);
        //获取时
        int hour=ca.get(Calendar.HOUR);
        //获取分
        int minute=ca.get(Calendar.MINUTE);
        //获取秒
        int second=ca.get(Calendar.SECOND);
        //获取Date对象，返回月 日 时：分：秒 年
        System.out.println(ca.getTime());
        //返回自1979.1.1以来的毫秒数
        System.out.println(ca.getTimeInMillis());
        //设置方法一，set(f,value)设置时间字段对应的值,f为时间字段，value为值
        ca.set(Calendar.YEAR, 2022);//自定义时间为2022年
        //查看设置的时间字段是否生效，此时变为2022年
        System.out.println(ca.get(Calendar.YEAR));
        //设置方法二，设置年月份,注意：11表示实际月份（12）-1
        ca.set(2022, 11, 15);
        //获取Date对象查看结果
        System.out.println(ca.getTime());
        //对月份加1
        操作,月份变化同时年份也变成了2023年
        ca.add(Calendar.MONDAY, +1);
        //查看结果
        System.out.println(ca.getTime());
        //重新设置为2022年12月15号
        ca.set(2022, 11, 15);
        //对月份加一操作，月份变化，而年份并没有变化。还是2022年
        ca.roll(Calendar.MONDAY, +1);
        //查看结果
        System.out.println(ca.getTime());
    }
}
```

>
> Calendar 小结：
>
> 1、`先实例化`Calender 才能使用，实例化方式有两种
>
> 2、Calendar 中的`add`方法调用会影响到其他字段
>
> 3、Calendar 中的`roll`方法只影响自身，不会影响其他字段
>
> 4、Calendar 的月份字段是`0-11`月，获取时需要加 1 操作。设置时，默认是实际月份减一

### 2、java.time 包

> java.time 包的主要类

<table><thead data-darkmode-bgcolor-16278328963292="rgba(56, 56, 56, 0.05)" data-darkmode-original-bgcolor-16278328963292="#fff|rgba(0, 0, 0, 0.05)" data-style="box-sizing: border-box; line-height: 1.5; background: rgba(0, 0, 0, 0.05);"><tr data-darkmode-bgcolor-16278328963292="rgba(56, 56, 56, 0.05)" data-darkmode-original-bgcolor-16278328963292="#fff|rgba(0, 0, 0, 0.05)"><td width="212.66666666666666" data-darkmode-bgcolor-16278328963292="rgba(56, 56, 56, 0.05)" data-darkmode-original-bgcolor-16278328963292="#fff|rgba(0, 0, 0, 0.05)" data-style="box-sizing: border-box; padding: 4px 8px; line-height: 1.5; font-size: 12.8px; border-color: rgb(223, 223, 223);">类包</td><td width="261.6666666666667" data-darkmode-bgcolor-16278328963292="rgba(56, 56, 56, 0.05)" data-darkmode-original-bgcolor-16278328963292="#fff|rgba(0, 0, 0, 0.05)" data-style="box-sizing: border-box; padding: 4px 8px; line-height: 1.5; font-size: 12.8px; border-color: rgb(223, 223, 223);">表示内容</td></tr></thead><tbody><tr><td width="126.99999999999999" data-style="box-sizing: border-box; padding: 4px 8px; line-height: 1.5; font-size: 12.8px; border-color: rgb(223, 223, 223);">LocalDate</td><td width="240.66666666666669" data-style="box-sizing: border-box; padding: 4px 8px; line-height: 1.5; font-size: 12.8px; border-color: rgb(223, 223, 223);">日期类</td></tr><tr><td width="126.99999999999999" data-style="box-sizing: border-box; padding: 4px 8px; line-height: 1.5; font-size: 12.8px; border-color: rgb(223, 223, 223);">LocalTime</td><td width="240.66666666666669" data-style="box-sizing: border-box; padding: 4px 8px; line-height: 1.5; font-size: 12.8px; border-color: rgb(223, 223, 223);">时间类</td></tr><tr><td width="126.99999999999999" data-style="box-sizing: border-box; padding: 4px 8px; line-height: 1.5; font-size: 12.8px; border-color: rgb(223, 223, 223);">LocalDateTime</td><td width="240.66666666666669" data-style="box-sizing: border-box; padding: 4px 8px; line-height: 1.5; font-size: 12.8px; border-color: rgb(223, 223, 223);">LocalDate+LocalTime（日期 + 时间）</td></tr><tr><td width="126.99999999999999" data-style="box-sizing: border-box; padding: 4px 8px; line-height: 1.5; font-size: 12.8px; border-color: rgb(223, 223, 223);">Instant</td><td width="240.66666666666669" data-style="box-sizing: border-box; padding: 4px 8px; line-height: 1.5; font-size: 12.8px; border-color: rgb(223, 223, 223);">时间戳</td></tr></tbody></table>

> LocalDate 常用的方法

```
从默认时区的系统时钟获取当前日期 : 年 - 月 - 日
```

```
import java.time.LocalDate;
import java.time.Month;
public class LocalDate {
    public static void main(String[] args) {
        //当前时间,比如:2021-07-15
        LocalDate today=LocalDate.now();
        //根据指定时间创建LocalDate,错误时间会报异常
        LocalDate firstDay=LocalDate.of(2022, Month.JANUARY, 1);
        //根据年份的指定天数，返回具体年月份
        LocalDate day=LocalDate.ofYearDay(2021, 100);
        //获取当前年份
        System.out.println(today.getYear());
        //获取当前的月份
        System.out.println(today.getMonth());
    }
}
```

> LocalTime 常用的方法

```
从默认时区的系统时钟获取当前的时间：时: 分: 秒. 纳秒
```

```
import java.time.LocalTime;
public class LocalTimeTest {
    public static void main(String[] args) {
        //当前时间,时:分:秒.纳秒
        LocalTime totime=LocalTime.now();
        //根据指定时间创建LocalTime,错误时间会报异常
        LocalTime time=LocalTime.of(23, 23);
        System.out.println(totime);
        //获取当前小时
        System.out.println(totime.getHour());
        //获取当前分钟
        System.out.println(totime.getMinute());
        //获取当前秒数
        System.out.println(totime.getSecond());
        //获取当前纳秒
        System.out.println(totime.getNano());
    }
}
```

> LocalDateTime 的常用方法

```
从默认时区的系统时钟获取当前的日期时间
```

```
import java.time.LocalDateTime;
public class  LocalDateTimeTest{
    public static void main(String[] args) {
        //当前时间,时:分:秒.纳秒
        LocalDateTime todatetime=LocalDateTime.now();
        //根据指定时间创建LocalTime,错误时间会报异常
        LocalDateTime dateTime=LocalDateTime.of(2022,04,12,23, 23);
        System.out.println(todatetime);
        //显示2022-04-12T23:23
        System.out.println(dateTime);
        //获取当前的的时间是这一年第几天
        System.out.println(todatetime.getDayOfYear());
        //获取当前小时
        System.out.println(todatetime.getHour());
        //获取当前分钟
        System.out.println(todatetime.getMinute());
        //获取当前秒数
        System.out.println(todatetime.getSecond());
        //获取当前纳秒
        System.out.println(todatetime.getNano());
    }
}
```


二、格式化类
---------

我们格式化的讲解主要针对前面讲到的数字、字符串和时间这三个对象。

> java.text 包的 java.text.Format 的子类

**NumberFormat** ：数字格式化，`抽象类`

-DecimalFormat

**MessageFormat** ：字符串格式化

**DateFormat** ：时间 / 日期格式化 `抽象类`

-SimpleDateFormat

> java.time.format 包下

**DateTimeFormatter** ：时间 / 日期格式化

### 1、数字格式化

```
import java.text.DecimalFormat;
import java.text.NumberFormat;
public class  Test{
    public static void main(String[] args) {
        //实例化对象
        NumberFormat nf=NumberFormat.getInstance();
        //设置一个数字
        double d=123456789.12345;
        //区别:0表示有且只能有几位，如果内容没有，则用0补充
        DecimalFormat df1 = new DecimalFormat("00.00");
        DecimalFormat df2=new DecimalFormat("##.##");
        DecimalFormat df3=new DecimalFormat("#.#%");
        System.out.println(df1.format(0.006));//输出00.01
        System.out.println(df2.format(0.006));//输出0.01
        System.out.println(df3.format(0.0006));//输出0.1%
        //设置最大小数点个数
        nf.setMaximumFractionDigits(2);
        System.out.println(nf.format(d));//输出123,456,789.12
    }
}
```

> 小结： `0`和`#`的共同点和区别
>
> 共同点：0 和 #都表示占位符, 都采取四舍五入的方式
>
> 区别：`0`表示有且只能有几位，如果内容没有，则用 0 补充; 而`#`表示最多有几位

### 2、字符串格式化

支持多个参数 - 值对位复制文本 支持变量的自定义格式

```
import java.text.MessageFormat;
import java.util.Date;
public class  MessageTest{
    public static void main(String[] args) {
        //多个参数-值对位复制文本
        String message="{0} {1} {2} {3}";
        String[] array= {"I","love","you","!"};
        //捆绑
        String love=MessageFormat.format(message, array);
        System.out.println(love);
        //变量的自定义格式
         int planet = 7;
         String event = "a disturbance in the Force";
         //下面的0，1，2表示第几个元素，后面的参数位置
         //planet在第0位，new Date()在第1位，event则是第2位
         String result = MessageFormat.format("At {1,time} on {1,date}, "
                 + "there was {2} on planet {0,number,integer}.",
                 planet, new Date(), event);
         System.out.println(result);
    }
}
```

### 3、时间格式化

> DateFormat 的使用

```
设置格式化模板
```

```
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class  DateFormatTest{
    public static void main(String[] args){
        //设置一个时间字符串
        String strDate="2021-07-15 20:41:30.666";
        //设置时间格式化模板
        String p="yyyy-MM-dd HH:mm:ss.sss";
        //实例化模板
        SimpleDateFormat spdate=new SimpleDateFormat(p);    
        try {
        //使用parse()方法，返回对象为Date
            Date d = spdate.parse(strDate);
            //打印结果
            System.out.println(d);
            //使用format()将时间对象转换为字符串
            String s = spdate.format(d);
            //打印结果
            System.out.println(s);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
```

> DateTimeFormatter 的使用

```
设置格式化模板
```

```
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class  DateTimeFormatterTest{
    public static void main(String[] args){
        //设置一个时间字符串
        String strDate="2021-07-15";
        //设置时间格式化模板
        DateTimeFormatter dfm=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //设置模板二
        DateTimeFormatter dfm2=DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        //使用parse格式化对象
        LocalDate local=LocalDate.parse(strDate, dfm);
        //打印结果
        System.out.println(local);
        //使用format()将时间对象转换为字符串，使用模板二
        String s=local.format(dfm2);
        //打印结果
        System.out.println(s);
    }
}
```


每天进步一点点，下一篇 Java 数据结构的学习

![](https://mmbiz.qpic.cn/sz_mmbiz_gif/rVVgBxVy9rh8zH4gNN7hKfqTrZa2nibIYWzECicYgRW6f1kmDfgw405hPZhplVCs1TGAAPIF31OeLIMH2nTTfoVg/640?wx_fmt=gif)





![](https://mmbiz.qpic.cn/sz_mmbiz_jpg/rVVgBxVy9rh8zH4gNN7hKfqTrZa2nibIYe5tFiaoMACjoK1ZciasqcvBZBBA2Bgfnfauk1AmRTicgb6ThpZAba133w/640?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/sz_mmbiz_png/rVVgBxVy9rh8zH4gNN7hKfqTrZa2nibIY7ADOartJp2PcBZfxWlL7NNOHibWTA8hhtC9LYNaA1VoenibYSVLvZPLw/640?wx_fmt=png)

扫码关注我

主动笃职，认真负责、

求实进取，互动互助。

![](https://mmbiz.qpic.cn/mmbiz_gif/FIBZec7ucCiaOJv1xaGlYbnpgUyOXeX35h4VplHTjV7EHZam8F7iccGJDGuqbMZHrrHz6ljQoEmMWI0ic2CfkLkuA/640?wx_fmt=gif)