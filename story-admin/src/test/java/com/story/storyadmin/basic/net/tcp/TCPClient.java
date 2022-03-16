package com.story.storyadmin.basic.net.tcp;

/**
 * @author: lipan
 * @date: 2019-06-10
 * @description:
 *
 * TCP多线程编程模型：
 * 服务器端使用无限循环
 * 每次accept返回后，创建新的线程来处理客户端请求
 * 每个客户端请求对应一个服务线程
 * 使用是线程池可以提高运行效率
 *
 */
public class TCPClient {
}
