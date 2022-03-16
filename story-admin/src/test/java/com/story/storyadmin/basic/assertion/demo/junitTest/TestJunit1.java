package com.story.storyadmin.basic.assertion.demo.junitTest;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;




/**
 * @author: lipan
 * @date: 2019-06-18
 * @description:
 */
public class TestJunit1 {
    @Test
    public void testAdd() {
        //packagetest data
        int num= 5;
        String temp= null;
        String str= "Junit is working fine";

        //check for equality
        assertEquals("Junit is working fine", str);

        //check for false condition
        assertFalse(num > 6);

        //check for not null value
        assertNotNull(str);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestJunit1.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
