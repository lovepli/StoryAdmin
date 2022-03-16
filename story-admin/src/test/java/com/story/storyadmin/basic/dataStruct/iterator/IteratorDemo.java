package com.story.storyadmin.basic.dataStruct.iterator;


/**
 * @author: lipan
 * @date: 2019-06-12
 * @description:
 *
 * Iterator 迭代
 * for...each循环是编译器实现的Iterator模式
 * 适用于for...each循环的类:
 * 实现Iterable接口
 *
 * 返回Iterator实例 好处:
 * 对任何集合都采用同一种访问模型
 * 调用者对集合内部结构一无所知
 * 集合类返回的Iterator对象知道如何迭代
 * Iterator是一种抽象的数据访问模型
 */

public class IteratorDemo {
    public static void main(String[] args) {
        //自己定义一个list 实现Iterable接口
        ReadOnlyLIst<String> list = new ReadOnlyLIst<>("apple", "pear", "orange");
        for (String s : list) {
            System.out.println(s);
        }
    }
}
