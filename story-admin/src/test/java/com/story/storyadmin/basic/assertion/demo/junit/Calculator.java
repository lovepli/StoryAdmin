package com.story.storyadmin.basic.assertion.demo.junit;

import java.util.Arrays;

/**
 * @author: lipan
 * @date: 2019-06-09
 * @description: 这个Calculator的对象的测试类为CalculatorTest
 */
public class Calculator {

    public int calculate(String expression) {
        String[] ss = expression.split("\\+");
        System.out.println(expression + " => " + Arrays.toString(ss));
        int sum = 0;
        for (String s : ss) {
            sum = sum + Integer.parseInt(s.trim());
        }
        return sum;
    }

    public static void main(String[] args) {
        Calculator c = new Calculator();
        int r = c.calculate("1+2+3");
        System.out.println(r);
    }
}
