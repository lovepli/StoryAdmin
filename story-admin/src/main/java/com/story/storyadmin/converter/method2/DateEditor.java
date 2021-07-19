package com.story.storyadmin.converter.method2;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: 59688
 * @date: 2021/7/14
 * @description: 自定义属性编辑器
 */
public class DateEditor extends PropertyEditorSupport {

    //将传入的字符串类数据转换为Date类型
    @Override
    public void setAsText(String  text) throws IllegalArgumentException{
        SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date= dateFormat.parse(text);
            setValue(date);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // 在控制器初始化时注册属性编辑器
    //public class UserController{
    //    @InitBinder
    //    public void initBuilder(WebDataBinder binder){
    //        // 注册自定义编辑器
    //        binder.registerCustomEditor(Date.class,new DateEditor());
    //    }
    //}


}
