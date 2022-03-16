package com.story.storyadmin.设计模式.享元模式;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: lipan
 * @date: 2021/8/23
 * @description: 图书馆
 */
public class Llibrary {
    private Map<String, Book> bookMap = new HashMap<>();

    private Llibrary() {
    }

    //只能有一个图书馆
    public static Llibrary getInstance() {
        return LazyHolder.LAZY_STATIC_SINGLETON;
    }

    //通过书名name来借书
    public Book libToBorrow(String name) {
        Book book;
        //如果图书馆有，直接把书借走
        if (bookMap.containsKey(name)) {
            book = bookMap.get(name);
        } else {//图书馆没有，则录入一本书，然后把书借走
            book = new ConcreteBook(name);
            bookMap.put(name, book);
        }
        return book;
    }

    //返回还有多少本书
    public int bookSize() {
        return bookMap.size();
    }
    private static class LazyHolder {
        private static final Llibrary LAZY_STATIC_SINGLETON = new Llibrary();
    }
}
