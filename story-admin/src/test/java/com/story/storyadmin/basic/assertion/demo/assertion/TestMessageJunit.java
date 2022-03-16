package com.story.storyadmin.basic.assertion.demo.assertion;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import org.junit.runner.notification.Failure;
import static org.junit.Assert.assertEquals;
/**
 * @author: lipan
 * @date: 2019-06-18
 * @description:
 */
public class TestMessageJunit {

    String message = "Hello World";
    MessageUtil messageUtil = new MessageUtil(message);

    @Test
    public void testPrintMessage() {
        assertEquals(message,messageUtil.printMessage());
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestMessageJunit.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
