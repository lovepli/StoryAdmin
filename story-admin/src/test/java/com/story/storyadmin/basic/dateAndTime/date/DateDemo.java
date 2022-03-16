package com.story.storyadmin.basic.dateAndTime.date;

import java.util.Date;

/**
 * @author: lipan
 * @date: 2019-06-12
 * @description:
 *
 * Date
 * Epoch Time:从1970-01-01 00:00:00 GMT+0:00开始到现在的秒数
 * JDK有新旧两套API处理日期和时间
 * java.util.Date表示日期和时间，内部使用long表示epoch毫秒数
 * Date和long的转换
 * SimpleDateFormat:用于Date和String的解析和格式化
 * 格式化字符串的定义: https://docs.oracle.com/javase/8/docs/api/java/text/SimpleDateFormat.html
 * Locale表示用户所在地区的日期、时间、数字、货币等格式。
 */
public class DateDemo {
    public static void main(String[] args) {
        //获取系统当前时间戳：
        System.out.println(System.currentTimeMillis());
        //获得当前时间：
        Date now = new Date();
        System.out.println(now);
        //把Date转化为long:
        long t = now.getTime();
        System.out.println(t);
        //把long转化为Date
        System.out.println(new Date(t));

    }
}
