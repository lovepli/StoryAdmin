package com.story.storyadmin.copybean.beanUtilsTest.demo2;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

import java.util.*;

/**
 * @author: lipan
 * @date: 11:47 下午
 * @description: BeanUtils.copyProperties不支持复制集合的解决方案 https://mp.weixin.qq.com/s/TsQceeeFSxBbCjneBP-0Nw
 * 测试BeanUtils.copyProperties是否支持复制数组和集合，还有解决方案
 */
@DisplayName("Junit5测试")
public class test {

    @Test
    @DisplayName("测试1")
    public void test(){
        // 测试数组的复制
        People[] member = new People[3];
        member[0] = new People(1, "老师", 30, 1);
        member[1] = new People(2, "班长", 15, 1);
        member[2] = new People(3, "学生", 15, 1);
        People[] member1 = new People[]{};
        BeanUtils.copyProperties(member, member1);
        System.out.println("是否可以复制数组：" + (member1.length == 0 ? false : true));
        // 测试List的复制（Map也不能复制，测试略）
        List<People> student = new ArrayList<>();
        student.add(member[1]);
        student.add(member[2]);
        List<People> student1 = new ArrayList<>();
        BeanUtils.copyProperties(student, student1);
        System.out.println("BeanUtils.copyProperties是否可以复制List：" + (student1.isEmpty() ? false : true));
        // 通过JSON工具实现List的复制（不仅仅是List，数组和Map等也可以通过类似方法实现复制，需要有无参构造方法，否则报错）
        student1 = JSON.parseArray(JSON.toJSONString(student), People.class);
        System.out.println("通过JSON工具复制List：" + student1);
        System.out.println("通过JSON工具是否深复制：" + (student.get(0) != student1.get(0) ? true : false));
        // 测试是否深复制
        Class source = new Class();
        source.setMember(member);
        source.setTeacher(member[0]);
        source.setStudent(student);
        Class target = new Class();
        BeanUtils.copyProperties(source, target);
        System.out.println("BeanUtils.copyProperties是否深复制：" + (source.getMember() != target.getMember() ? true : false));

        //
        //是否可以复制数组：false
        //BeanUtils.copyProperties是否可以复制List：false
        //通过JSON工具复制List：[People(id=2, name=班长, age=15, sex=1), People(id=3, name=学生, age=15, sex=1)]
        //通过JSON工具是否深复制：true
        //BeanUtils.copyProperties是否深复制：false
    }

    /**
     * 针对List的复制除了通过JSON工具，最简单的就是循环复制集合属性，下面测试两种方法的效率。
     *
     * 分别测试count=1、10、100、1000、10000、100000的结果。为了防止一起执行出现影响，每次只测试一种复制方法的一种情况，
     * 共执行12次。通过对比可以知道，通过JSON复制属性快于BeanUtils，
     *
     */
    @Test
    @DisplayName("测试2")
    public void test2(){
        int count = 1;
        System.out.println("测试数据长度：" + count);
        List<People> source = new LinkedList<>();
        List<People> target = new LinkedList<>();
        long start;

        for (int i = 0; i < count; i++) {
            source.add(new People(1, "ly", 25, 1));
        }

        start = System.nanoTime();
        target = JSON.parseArray(JSON.toJSONString(source), People.class);
        System.out.println("JSON：" + (System.nanoTime() - start));

        start = System.nanoTime();
        for (int i = 0; i < count; i++) {
            People p = new People();
            BeanUtils.copyProperties(source.get(i), p);
            target.add(p);
        }
        System.out.println("BeanUtils.copyProperties" + (System.nanoTime() - start));
    }

    @Test
    @DisplayName("测试3")
    public void test3(){
        List<People> source = new LinkedList<>();
        List<People> target = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            source.add(new People(1, "ly", 25, 1));
        }
        target = copyList(source);
        target.forEach(System.out::println);
    }

    @Test
    @DisplayName("测试4")
    public void test4(){
        Map<String,People> source = new HashMap<>();
        Map<String, Object> target = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            source.put(i+"",new People(1, "ly", 25, 1));
        }
        target = copyMap(source);
        System.out.println(JSON.toJSONString(target));

    }


    /**
     * 通过以下方式解决复制List、Map
     * @param list
     * @param <T>
     * @return
     */
    public static <T> List copyList(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList();
        }
        return JSON.parseArray(JSON.toJSONString(list), list.get(0).getClass());
    }

    public static Map<String, Object> copyMap(Map map) {
        return JSON.parseObject(JSON.toJSONString(map));
    }


}
