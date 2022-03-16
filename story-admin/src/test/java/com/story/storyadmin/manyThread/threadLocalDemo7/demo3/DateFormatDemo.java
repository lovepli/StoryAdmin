package com.story.storyadmin.manyThread.threadLocalDemo7.demo3;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: 59688
 * @date: 2021/7/30
 * @description:
 */
public class DateFormatDemo {

    private static SimpleDateFormat DATE_FORMAT=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private int seconds;

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public DateFormatDemo(int seconds) {
        this.seconds = seconds;
    }

    public String getDate(int seconds){
        // 参数的单位是毫秒，从1970.1.1 00:00:00 GMT计时
        Date date=new Date(1000*seconds);
        return DATE_FORMAT.format(date);
    }
    //上述代码我们使用到了固定线程数的线程池来执行时间格式化任务，我们来执行一下，看看结果：
    //      现执行结果中有很多重复的时间格式化内容，这是为什么呢？
    //
    //      这是因为SimpleDateFormat是一个线程不安全的类，其实例对象在多线程环境下作为共享数据，会发生线程不安全问题。
    //说到这里，很多读者肯定会说，我们可以尝试一下使用锁机制，我们将date方法内的格式化代码使用synchronized关键字概括起来，保证同一时刻只能有一个线程来访问SimpleDateFormat的format方法，代码如下所示：

    //使用锁机制，同步代码块
    public String getDate2(int seconds){
        Date date =new Date(1000*seconds);
        String format;
        synchronized (DateFormatDemo.class){
            format=DATE_FORMAT.format(date);
        }
        return format;
    }

    // 有了锁的保证，那么这次执行后就不会再出现重复的时间格式化结果了，这也就保证了线程安全。
    //
    // 使用锁机制确实可以解决问题，但是多数情况下，我们不大愿意使用锁，因为锁的使用会带来性能的下降（比如10个线程重复排队执行DATE_FORMAT.format(date)代码），
    // 那么有没有其他方法来解决这个问题呢？答案当然是有，那就是本文的主角——ThreadLocal。
}
