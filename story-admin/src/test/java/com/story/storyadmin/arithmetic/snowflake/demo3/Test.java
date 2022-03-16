package com.story.storyadmin.arithmetic.snowflake.demo3;

import org.springframework.stereotype.Component;

import java.text.ParseException;

/**
 * Description:
 *
 * @author yuange
 * @version 1.0
 * @date: 2019/10/30 15:57
 * @since JDK 1.8
 */
@Component
public class Test {

    /**
     * 雪花算法生成唯一id
     * @throws ParseException
     */
    @org.junit.Test
    public void test1() throws ParseException {
        String id = new SnowFlakeGenerator().nextId();
        System.out.println("id生成成功：" + id + ",长度：" + id.length());
    }

    /**
     * 测试多线程并发访问
     * @throws ParseException
     */
    @org.junit.Test
    public void test2() throws ParseException {
        SnowFlakeGenerator snowFlakeGenerator  = new SnowFlakeGenerator();

        Thread threadA  = new Thread(() ->{
            String id = null;
                id = snowFlakeGenerator.nextId();
                System.out.println("id生成成功：" + id + ",长度：" + id.length());
        });

        Thread threadB  = new Thread(() ->{
            String id = null;
                id = snowFlakeGenerator.nextId();
                System.out.println("id生成成功：" + id + ",长度：" + id.length());
        });

        threadA.start();
        threadB.start();
    }


    /*@org.junit.Test
    public void test2() {
        Date formatDate = DateTimeUtils.getFormatDate("2081-01-01");
        long time = formatDate.getTime();
        System.out.println(time);
    }*/
}
