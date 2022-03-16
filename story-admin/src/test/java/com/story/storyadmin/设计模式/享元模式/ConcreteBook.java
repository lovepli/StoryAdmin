package com.story.storyadmin.设计模式.享元模式;

/**
 * @author: lipan
 * @date: 2021/8/23
 * @description:
 */
public class ConcreteBook implements Book {
    //被借出去的书名
    private String name;

    public ConcreteBook(String name) {
        this.name = name;
    }

    @Override
    public void borrow() {
        System.out.println("图书馆借出去一本书，书名："+this.name);
    }
}
