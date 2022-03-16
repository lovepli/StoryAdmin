package com.story.storyadmin.设计模式.观察者模式.demo2;

/**
 * @author: lipan
 * @date: 2021/8/23
 * @description:
 */
public class JDKObserverTest {
    public static void main(String[] args) {
        App app = new App("微信");
        Message message = new Message("端午安康！");

        User tian = new User("田哥");
        app.addObserver(tian);

        User zhang = new User("勇哥");
        app.addObserver(zhang);

        User li = new User("苗哥");
        app.addObserver(li);

        User xi = new User("西哥");
        app.addObserver(xi);

        User bing = new User("兵哥");
        app.addObserver(bing);

        app.publishMsg(message);
    }
}
