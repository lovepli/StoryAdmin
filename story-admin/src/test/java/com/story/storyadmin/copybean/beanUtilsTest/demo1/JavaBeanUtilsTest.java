package com.story.storyadmin.copybean.beanUtilsTest.demo1;

import cn.hutool.core.bean.BeanUtil;
import com.story.storyadmin.utils.objectMethordUtil.JavaBeanUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: lipan
 * @date: 11:22 下午
 * @description:  使用BeanUtils.copyProperties进行对象之间的属性赋值
 */
@DisplayName("Junit5测试")
public class JavaBeanUtilsTest {


    @Test
    @DisplayName("测试")
    public void test(){
        Employee ee1=new Employee("A",21,"it");
        Employee ee2=new Employee("B",23,"account");
        User user=new User();
        BeanUtil.copyProperties(ee1, user);
        System.out.println(user);
        System.out.println("-------------分割线--------------");
        List<User> output=new ArrayList<>();
        List<Employee> source= Arrays.asList(ee1,ee2);
        output= JavaBeanUtils.convertList2List(source,User.class);
        for (User str:output) {
            System.out.println(str);
        }
    }

    //User{name='A', age=21}
    //-------------分割线--------------
    //User{name='A', age=21}
    //User{name='B', age=23}




}
