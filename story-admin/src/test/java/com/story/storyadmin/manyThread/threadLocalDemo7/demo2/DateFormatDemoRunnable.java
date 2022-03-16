package com.story.storyadmin.manyThread.threadLocalDemo7.demo2;

import java.util.concurrent.Callable;

/**
 * @author: 59688
 * @date: 2021/7/30
 * @description:
 */
public class DateFormatDemoRunnable implements Callable<String> {

    private DateFormatDemo dateFormatDemo;

    public DateFormatDemoRunnable(DateFormatDemo dateFormatDemo){
        this.dateFormatDemo=dateFormatDemo;
    }

    @Override
    public String call() throws Exception {
        return dateFormatDemo.getDate(dateFormatDemo.getSeconds());
    }
}
