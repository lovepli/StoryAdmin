package com.story.storyadmin.designPatterns.payService9;

/**
 * @author: lipan
 * @date: 2021/8/20
 * @description:
 * 函数式编程的策略优化模式
 *
 * 我们可以对上面的代码进行稍作修改，采用java8里的函数式编程方法来简化代码。
 *
 * 首先定义一个interface接口。
 */
public interface Strategy9 {

    void run() throws Exception;

}
