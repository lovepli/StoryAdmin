package com.story.storyadmin.converter.methord3;

import com.story.storyadmin.converter.method2.DateEditor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;

import java.util.Date;

/**
 * @author: 59688
 * @date: 2021/7/14
 * @description: 使用 WebBindingInitializer 注册全局自定义编辑器转换数据
 */
//@Component //@Component 注解作用：把资源交给spring 来管理。相当于在 xml 中配置一个 bean。
public class DateBindingInitializer implements WebBindingInitializer { // 实现WebBindingInitializer 接口
    @Override
    public void initBinder(WebDataBinder webDataBinder) {
            // 注册自定义编辑器
        webDataBinder.registerCustomEditor(Date.class,new DateEditor());
    }
}
