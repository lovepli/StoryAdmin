package com.story.storyadmin.framework.tcp.demo2;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author Nxy
 * @Date 2020/3/21 20:11
 * @Description 读取 接收缓冲区 接收数据行为策略
 */
public interface ReciveRegister {
    public byte[] read(InputStream in) throws IOException;
}