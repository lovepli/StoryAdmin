package com.story.storyadmin.basic.dataStruct.stack;

/**
 * @author: lipan
 * @date: 2019-06-12
 * @description:
 *
 * Stack
 * 栈(Stack)是一种后进先出(LIFO)的数据结构
 * 操作栈的元素的方法: push(E e):压栈 pop():出栈* peek():取栈顶元素但不出栈
 * Java使用Deque实现栈的功能，注意只调用push/pop/peek，避免调用Deque的其他方法
 * 不要使用遗留类Stack
 *
 * Stack的作用：
 * 将中缀表达式编译为后缀表达式   计算机中就是这样处理运算的，他不会直接执行中缀表达式，而是转换为后缀表达式来执行运算程序
 *     中缀表达式：1 + 2 *（9 - 5）
 *     后缀表达式：1 2 9 5 - * +
 */
public class StackDemo {
    public static void main(String[] args) {

    }
}
