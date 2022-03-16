package com.story.storyadmin.framework.tcp.demo2;

/**
 * @Author Nxy
 * @Date 2020/3/21 20:31
 * @Description 报文定界策略类型
 */
public enum DelimitType {
    //通过长度标识接收数据
    LengthFlag,
    //将数据封装为帧，接收定长数据
    fixedLength,
    //通过结束标识接收数据
    commonFlag
}