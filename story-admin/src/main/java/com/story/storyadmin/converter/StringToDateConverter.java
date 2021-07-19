package com.story.storyadmin.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: 59688
 * @date: 2021/7/12
 * @description: 自定义类型转换器，将传递的字符串转换为Date类型
 * spring 目前支持 3 中类型转换器：
 * Converter<S,T>：将 S 类型对象转为 T 类型对象
 * ConverterFactory<S, R>：将 S 类型对象转为 R 类型及子类对象
 * GenericConverter：它支持多个 source 和目标类型的转化，同时还提供了 source 和目标类型的上下文，这个上下文能让你实现基于属性上的注解或信息来进行类型转换。
 *
 * 这 3 种类型转换器使用的场景不一样，我们以Converter<S,T>为例。假如：接口中接收参数的实体对象中，有个字段的类型是 Date，但是实际传参的是字符串类型：2021-01-03 10:20:15，要如何处理呢？
 */
public class StringToDateConverter implements Converter<String, Date> {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Date convert(String source) {
        if (source != null && !"".equals(source)) {
            try {
                simpleDateFormat.parse(source);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}