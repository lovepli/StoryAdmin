package com.story.storyadmin.设计模式.观察者模式.demo2;

/**
 * @author: lipan
 * @date: 2021/8/23
 * @description:
 */
//消息
public class Message {
    private String content;

    public Message(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
