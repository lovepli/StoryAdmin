package com.story.storyadmin.basic.dateAndTime.localDateTime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author: lipan
 * @date: 2019-06-12
 * @description:
 */
public class Format {
    public static void main(String[] args) {
        //格式化
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dtf.format(LocalDateTime.now()));
        //按iso格式解析
        LocalDateTime dt1 = LocalDateTime.parse("2016-11-30T15:16:17");
        System.out.println(dt1);
        //按照指定格式解析
        LocalDateTime dt2 = LocalDateTime.parse("2019-11-30 17:12:12",dtf);
        System.out.println(dt2);

    }
}
