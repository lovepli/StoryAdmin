package com.story.storyadmin.basic.dateAndTime.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: lipan
 * @date: 2019-06-12
 * @description:
 */
public class FormatDate {
    public static void main(String[] args) {
        //获得当前时间：
        Date now = new Date();
        //指定格式打印：
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//年月日 时分秒
        System.out.println(sdf.format(now));

        SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        System.out.println(sdf2.format(now));

        SimpleDateFormat sdf3 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss z");  //时区
        System.out.println(sdf3.format(now));

        SimpleDateFormat sdf4 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss Z");
        System.out.println(sdf4.format(now));

    }
}
