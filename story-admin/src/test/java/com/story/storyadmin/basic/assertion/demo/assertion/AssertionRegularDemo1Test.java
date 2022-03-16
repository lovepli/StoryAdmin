package com.story.storyadmin.basic.assertion.demo.assertion;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
public class AssertionRegularDemo1Test {

    @Test
    public double abs(double d) {
        return d >= 0 ? d : -d;
    }

    @Test
    public void main() {
        double x = abs(-123.45); //这里引用的是AssertionDemo里面定义的方法
        assert x >= 0 : "x must >= 0 but x = " + x;
        System.out.println(x);
    }
}