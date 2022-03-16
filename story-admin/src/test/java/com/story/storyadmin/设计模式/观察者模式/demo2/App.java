package com.story.storyadmin.设计模式.观察者模式.demo2;
import java.util.Observable;
/**
 * @author: lipan
 * @date: 2021/8/23
 * @description: 被观察者
 */

//APP平台
public class App extends Observable {

    private String name;

    public App(String name) {
        this.name = name;
    }

    public void publishMsg(Message message) {
        System.out.println(this.name + " 平台 给 用户们发送消息");
        //setChanged是Observable中的方法
        setChanged();
        //notifyObservers也是Observable中的方法
        notifyObservers(message);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
