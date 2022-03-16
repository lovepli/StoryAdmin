package com.story.storyadmin.basic.assertion.demo.assertion.packagetest;

/**
 * @author: lipan
 * @date: 2019-06-18
 * @description:
 */
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestJunit2 {

    String message = "Robert";
    MessageUtil messageUtil = new MessageUtil(message);

    @Test
    public void testSalutationMessage() {
        System.out.println("Inside testSalutationMessage()");
        message = "Hi!" + "Robert";
        assertEquals(message,messageUtil.salutationMessage());
    }
}