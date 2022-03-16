package com.story.storyadmin.设计模式.观察者模式.demo2;

import java.util.Observable;
import java.util.Observer;
/**
 * @author: lipan
 * @date: 2021/8/23
 * @description: 观察者
 */
public class User implements Observer {

    private String userName;

    public User(String userName) {
        this.userName = userName;
    }
    //实现了Observer的update方法
    @Override
    public void update(Observable o, Object arg) {
        App app=(App)o;
        Message message=(Message)arg;
        System.out.println(this.userName+" 收到 "+ app.getName()+" 平台 的消息，内容："+message.getContent());
    }

}