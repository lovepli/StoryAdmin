package com.story.storyadmin.basic.dateAndTime.date;

import java.util.Date;

/**
 * @author: lipan
 * @date: 2019-06-12
 * @description:
 */
public class DateToString {
    public static void main(String[] args) {

        //获得当前时间：
        Date now = new Date();
        //以当前时区打印日期时间：
        System.out.println(now.toString());
        //以GMT+00:00时区打印日期时间
        System.out.println(now.toGMTString());
        //以当前时区+当前Locale打印日期时间
        System.out.println(now.toLocaleString());


    }
}
