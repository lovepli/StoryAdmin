package com.story.storyadmin.basic.assertion.demo.assertion;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * @author: lipan
 * @date: 2019-06-18
 * @description:
 *
 * 观察以下的输出，这是 JUnite 执行过程：
 *
 * beforeClass() 方法首先执行，并且只执行一次。
 * afterClass() 方法最后执行，并且只执行一次。
 * before() 方法针对每一个测试用例执行，但是是在执行测试用例之前。
 * after() 方法针对每一个测试用例执行，但是是在执行测试用例之后。
 * 在 before() 方法和 after() 方法之间，执行每一个测试用例。
 */
public class ExecutionProcedureJunit {

    //execute only once, in the starting
    @BeforeClass
    public static void beforeClass() {
        System.out.println("in before class");
    }

    //execute only once, in the end
    @AfterClass
    public static void  afterClass() {
        System.out.println("in after class");
    }

    //execute for each packagetest, before executing packagetest
    @Before
    public void before() {
        System.out.println("in before");
    }

    //execute for each packagetest, after executing packagetest
    @After
    public void after() {
        System.out.println("in after");
    }

    //packagetest case 1
    @Test
    public void testCase1() {
        System.out.println("in packagetest case 1");
    }

    //packagetest case 2
    @Test
    public void testCase2() {
        System.out.println("in packagetest case 2");
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(ExecutionProcedureJunit.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}