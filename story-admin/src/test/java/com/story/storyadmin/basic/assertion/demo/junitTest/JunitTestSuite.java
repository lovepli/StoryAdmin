package com.story.storyadmin.basic.assertion.demo.junitTest;

import junit.framework.TestResult;
import junit.framework.TestSuite;

/**
 * @author: lipan
 * @date: 2019-06-18
 * @description:
 */
public class JunitTestSuite {
    public static void main(String[] a) {
        // add the packagetest's in the suite
        TestSuite suite = new TestSuite(TestJunit1.class, TestJunit2.class, TestJunit3.class );
        TestResult result = new TestResult();
        suite.run(result);
        System.out.println("Number of packagetest cases = " + result.runCount());
    }
}
