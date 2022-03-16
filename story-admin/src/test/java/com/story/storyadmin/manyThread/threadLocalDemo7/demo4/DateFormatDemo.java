package com.story.storyadmin.manyThread.threadLocalDemo7.demo4;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: 59688
 * @date: 2021/7/30
 * @description:
 */
public class DateFormatDemo {

    private static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = new ThreadLocal <SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    // 上面的代码使用到了ThreadLocal，将SimpleDateFormat对象用ThreadLocal包装了一层，使得多个线程内部都有
    // 一个SimpleDateFormat对象副本，每个线程使用自己的SimpleDateFormat，这样就不会产生线程安全问题了。
    // 那么以上介绍的是ThreadLocal的第一大场景的使用，也就是利用到了ThreadLocal的initialValue()方法，使得每个线程内都具备了一个SimpleDateFormat副本。

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

    public String getDate(int seconds) {
        // 参数的单位是毫秒，从1970.1.1 00:00:00 GMT计时
        Date date = new Date(1000 * seconds);
        SimpleDateFormat simpleDateFormat = dateFormatThreadLocal.get();
        return simpleDateFormat.format(date);
    }
}
