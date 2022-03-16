package com.story.storyadmin.arithmetic.snowflake.demo1;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 美女同事用单例模式花式实现雪花算法 https://mp.weixin.qq.com/s/HFQXadkkQnRkyuOlqQ8FBg
 * @desc: 饿汉式单例模式实现雪花算法
 * @author: 公众号：赫连小伍
 * @create: 2021-06-29 19:32
 **/
public class SnowFlake {

    private static SnowFlake snowFlake = new SnowFlake();

    private SnowFlake() {}

    public static SnowFlake getInstance() {
        return snowFlake;
    }

    // 序列号，同一毫秒内用此参数来控制并发
    private long sequence = 0L;
    // 上一次生成编号的时间串，格式：yyMMddHHmmssSSS
    private String lastTime = "";

    public synchronized String getNum() {
        String nowTime = getTime(); // 获取当前时间串，格式：yyMMddHHmmssSSS
        String machineId = "01"; // 机器编号，这里假装获取到的机器编号是2。实际项目中可从配置文件中读取
        // 本次和上次不是同一毫秒，直接生成编号返回
        if (!lastTime.equals(nowTime)) {
            sequence = 0L; // 重置序列号，方便下次使用
            lastTime = nowTime; // 更新时间串，方便下次使用
            return new StringBuilder(nowTime).append(machineId).append(sequence).toString();
        }
        // 本次和上次在同一个毫秒内，需要用序列号控制并发
        if (sequence < 99) { // 序列号没有达到最大值，直接生成编号返回
            sequence = sequence + 1;
            return new StringBuilder(nowTime).append(machineId).append(sequence).toString();
        }
        // 序列号达到最大值，需要等待下一毫秒的到来
        while (lastTime.equals(nowTime)) {
            nowTime = getTime();
        }
        sequence = 0L; // 重置序列号，方便下次使用
        lastTime = nowTime; // 更新时间串，方便下次使用
        return new StringBuilder(nowTime).append(machineId).append(sequence).toString();
    }

    private String getTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmssSSS"));
    }
}