package com.story.storyadmin.设计模式.享元模式;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lipan
 * @date: 2021/8/23
 * @description:
 */
public class Student {
    private static List<Book> bookList = new ArrayList<>();
    private static BookFactory bookFactory;

    public static void main(String[] args) {
        bookFactory = BookFactory.getInstance();

        studenBorrow("java 从入门到精通");
        studenBorrow("java 从入门到放弃");
        studenBorrow("JVM java虚拟机");
        studenBorrow("java编程思想");


        //还了后，再借一次
        studenBorrow("java 从入门到精通");
        studenBorrow("java 从入门到放弃");
        studenBorrow("JVM java虚拟机");
        studenBorrow("java编程思想");

        //还了后，再借一次
        studenBorrow("java 从入门到精通");
        studenBorrow("java 从入门到放弃");
        studenBorrow("JVM java虚拟机");
        studenBorrow("java编程思想");

        //把每一本书借出去
        for (Book book:bookList){
            book.borrow();
        }

        System.out.println("学生一共借了 "+bookList.size()+"本书");
        System.out.println("学生一共借了 "+ bookFactory.bookSize()+"本书");

    }

    private static void studenBorrow(String name) {
        bookList.add(bookFactory.libToBorrow(name));
    }
}
