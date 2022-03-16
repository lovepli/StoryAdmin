package com.story.storyadmin.design.mediator;

import cn.hutool.core.lang.Console;

/**
 * Description:
 *
 * @author Lvshen
 * @version 1.0
 * @date: 2020/10/8 13:59
 * @since JDK 1.8
 */
public class ConcreteColleague2 extends Colleague {
    @Override
    public void receive() {
        Console.log("具体同事类2收到请求。");
    }

    @Override
    public void send() {
        Console.log("具体同事类2发出请求。");
        mediator.relay(this); //请中介者转发
    }
}
