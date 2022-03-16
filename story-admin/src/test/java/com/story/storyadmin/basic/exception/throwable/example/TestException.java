package com.story.storyadmin.basic.exception.throwable.example;

/**
 * @author: lipan
 * @date: 2019-11-20
 * @description: 例子博客：https://blog.csdn.net/qq_36186690/article/details/81172500
 */

//假如程序员a提供了一个方法借口供程序员b使用，b用它实现某些功能，最后呈现给客户c。
public class TestException {
    /**
     * b呈现给客户c的方法。假设我们的打印是呈现给c的
     * @param args
     * @throws Exception
     */
    public static void main(String[] args){
        String num = "1234tr";
        System.out.println(numberFormat(num));
    }
    /**
     * a提供的数据转换的借口
     * @param num
     * @return
     */
    public static int  numberFormat(String num) {
        int x = Integer.parseInt(num);
        return x;
    }

}