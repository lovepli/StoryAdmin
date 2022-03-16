package com.story.storyadmin.basic.assertion.demo.junitTest;

import junit.framework.AssertionFailedError;
import junit.framework.TestResult;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * @author: lipan
 * @date: 2019-06-18
 * @description:
 */
public class TestJunit3 extends TestResult {
    // add the error
    public synchronized void addError(Test test, Throwable t) {
        super.addError((junit.framework.Test) test, t);
    }

    // add the failure
    public synchronized void addFailure(Test test, AssertionFailedError t) {
        super.addFailure((junit.framework.Test) test, t);
    }
    @Test
    public void testAdd() {
        // add any packagetest
    }

    // Marks that the packagetest run should stop.
    public synchronized void stop() {
        //stop the packagetest here
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestJunit3.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}

