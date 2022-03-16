package com.story.storyadmin.basic.assertion.demo.assertion.packagetest;

/**
 * @author: lipan
 * @date: 2019-06-18
 * @description:
 */
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestJunit1 {

    String message = "Robert";
    MessageUtil messageUtil = new MessageUtil(message);

    @Test
    public void testPrintMessage() {
        System.out.println("Inside testPrintMessage()");
        assertEquals(message, messageUtil.printMessage());
    }
}
