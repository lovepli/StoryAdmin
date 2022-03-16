package com.story.storyadmin.formatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author: 59688
 * @date: 2021/7/14
 * @description: 自定义数据格式化器，将字符串格式化日期
 */
public class StringToDateFormatter implements Formatter<Date> {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // 显示Formatter<T> 的T对象
    @Override
    public String print(Date date, Locale locale) {
        return simpleDateFormat.format(date);
    }

    // 解析文本字符串，返回一个Formatter<T> 的T对象
    @Override
    public Date parse(String s, Locale locale) throws ParseException {
        try {
            return simpleDateFormat.parse(s);
        }catch (Exception e){
            throw  new IllegalArgumentException();
        }
    }


}
