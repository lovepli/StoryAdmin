package com.story.storyadmin.basic.dateAndTime.localDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author: lipan
 * @date: 2019-06-12
 * @description:
 *
 * ZonedDateTime
 * ZonedDateTime = LocalDateTime + ZoneId
 * ZonedDateTime可以做时区转换:withZoneSameInstant
 * ZoneId:新的时区对象
 * Instant:时刻，可以转换为long(注意单位是秒)
 * ZonedDateTime、Instant和long可以互相转换
 * Period和Duration表示一段时间，可用于日期和时间的加减
 * 其它底层API:
 * ZoneOffset
 * OffsetDateTime
 * OffsetTime
 * ChronoLocalDate
 * ChronoLocalDateTime
 */
public class LocalDateTimeDemo {
    public static void main(String[] args) {
        //获取当前本地时间
        LocalDate d1 = LocalDate.now();
        System.out.println(d1);
        System.out.println("week = "+d1.getDayOfWeek().getValue());
        //注意11月==11
        LocalDate d2 = LocalDate.of(2019, 6, 12);
        System.out.println(d2);

        //获取当前本地时间
        LocalTime t1 = LocalTime.now();
        System.out.println(t1);
        LocalTime t2 = LocalTime.of(17, 28, 33);
        System.out.println(t2);

        //获取当前本地时日期和时间
        LocalDateTime dt1 = LocalDateTime.now();
        System.out.println(dt1);
        //用localDate和LocalTime组合
        LocalDateTime dt2 = LocalDateTime.of(d2, t2);
        System.out.println(dt2);

    }
}
