package com.story.storyadmin.basic.dateAndTime.localDateTime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.TemporalAdjusters;

/**
 * @author: lipan
 * @date: 2019-06-12
 * @description:
 * LocalDateTime java.time提供了新的API:
 * LocalDate
 * LocalTime
 * LocalDateTime
 * ZonedDateTime
 * ZoneId
 * Instant
 * Formatter
 *
 * 新API特点:
 * 严格区分日期和时间
 * 不变类
 * 修复了Month和Week的常量值
 *
 * LocalDate:日期
 * LocalTime:时间
 * LocalDateTime:日期+时间
 * DateTimeFormatter:格式化日期和时间
 * 默认按照ISO 8601解析和格式化
 * 可以对日期和时间进行加减、调整、判断先后，计算Period L
 * ocalDateTime没有时区信息，无法与long进行转换
 */
public class Calculate {
    public static void main(String[] args) throws Exception{
        //获取当前时间和日期
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);
        //+ 5 天
        LocalDateTime ldt2 = ldt.plusDays(5);
        System.out.println(ldt2);
        //- 2 小时
        LocalDateTime ldt3 = ldt.minusHours(2);
        System.out.println(ldt3);

        //获得当月第一天
        LocalDate firstDay = LocalDate.now().withDayOfMonth(1);
        LocalDate firstDay2 = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(firstDay);
        System.out.println(firstDay.equals(firstDay2));

        //获得当月第最后一天
        LocalDate lastDay = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(lastDay);

        //获得当月第一个星期日
        LocalDate firstSunday = LocalDate.now().with(TemporalAdjusters.firstInMonth(DayOfWeek.SUNDAY));
        System.out.println(firstSunday);
        //判断两个日期哪个在前
        System.out.println(firstSunday.isBefore(LocalDate.now()));
        //两个日期相差 ？年？月？天
        Period p = LocalDate.now().until(LocalDate.of(2050, 1, 1));
        System.out.println(p);
        //两个日期一共相差多少天
        System.out.println(LocalDate.of(2050,1,1).toEpochDay() - LocalDate.now().toEpochDay());


    }
}
