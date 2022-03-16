package com.story.storyadmin.basic.dateAndTime.zonedDateTime;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author: lipan
 * @date: 2019-06-12
 * @description:
 */
public class ZonedDateTimeDemo {
    public static void main(String[] args) {
        //获取当前默认时区的日期和时间
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);

        //打印时区
        System.out.println(now.getZone());

        //获取Instant
        Instant ins = now.toInstant();
        System.out.println(ins.getEpochSecond());

        //按照指定时区获取当前日期和时间
        ZonedDateTime london = ZonedDateTime.now(ZoneId.of("Europe/London"));
        System.out.println(london);

        //把伦敦时间转换到纽约时间
        ZonedDateTime newyork = london.withZoneSameInstant(ZoneId.of("America/New_York"));
        System.out.println(newyork);
    }
}
