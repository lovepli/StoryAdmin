package com.story.storyadmin.manyThread.threadLocalDemo7.demo2;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: 59688
 * @date: 2021/7/30
 * @description:
 */
public class DateFormatDemo {

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
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm;ss");
        return dateFormat.format(date);
    }
}
