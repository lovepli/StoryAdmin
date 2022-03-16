package com.story.storyadmin.basic.assertion.demo.junitTest;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import org.junit.runner.notification.Failure;


/**
 * @author: lipan
 * @date: 2019-06-18
 * @description:
 */
public class TestJunit2 extends TestCase {
    protected double fValue1;
    protected double fValue2;

    @Before
    public void setUp() {
        fValue1= 2.0;
        fValue2= 3.0;
    }

    @Test
    public void testAdd() {
        //count the number of packagetest cases
        System.out.println("No of Test Case = "+ this.countTestCases());

        //packagetest getName
        String name= this.getName();
        System.out.println("Test Case Name = "+ name);

        //packagetest setName
        this.setName("testNewAdd");
        String newName= this.getName();
        System.out.println("Updated Test Case Name = "+ newName);
    }
    //tearDown used to close the connection or clean up activities
    public void tearDown(  ) {
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestJunit2.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}