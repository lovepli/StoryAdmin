package com.story.storyadmin.framework.jedisPool;

/**
 * 手写一个Jedis以及JedisPool https://mp.weixin.qq.com/s/4RjyyYVU95I7R7v9MKutEA
 * 测试我们自己写的客户端
 */
public class Main {

    private Jedis client = new Jedis("www.xxx.top",6380);

    //@Test
    public void set(){
        client.set("fantj","fantj");
        String result = client.get("fantj");
        System.out.println(result);
    }
    //@Test
    public void setNx(){
        client.set("fantj","fantj","NX","EX",10000);
        String result = client.get("fantj");
        System.out.println(result);
    }
    //@Test
    public void append(){
//        client.append("fantj","-2019");
        String fantj = client.get("fantj");
        System.out.println(fantj);
    }
    //@Test
    public void testChar(){
        System.out.println((char)42);
        System.out.println(((char)36));
    }
}