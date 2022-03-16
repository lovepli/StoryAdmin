package com.story.storyadmin.basic.assertion.demo.assertion;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * @author: lipan
 * @date: 2019-06-18
 * @description:
 */public class JunitAnnotation {

    //execute before class
    @BeforeClass
    public static void beforeClass() {
        System.out.println("in before class");
    }

    //execute after class
    @AfterClass
    public static void  afterClass() {
        System.out.println("in after class");
    }

    //execute before packagetest
    @Before
    public void before() {
        System.out.println("in before");
    }

    //execute after packagetest
    @After
    public void after() {
        System.out.println("in after");
    }

    //packagetest case
    @Test
    public void test() {
        System.out.println("in packagetest");
    }

    //packagetest case ignore and will not execute
    @Ignore
    public void ignoreTest() {
        System.out.println("in ignore packagetest");
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(JunitAnnotation.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
