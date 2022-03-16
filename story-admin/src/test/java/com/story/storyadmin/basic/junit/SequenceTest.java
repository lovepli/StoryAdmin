package com.story.storyadmin.basic.junit;

import org.junit.*;

/**
 * @author: lipan
 * @date: 2019-06-09
 * @description:
 */
public class SequenceTest {

    @BeforeClass
    public static void setUpBeforeClass() {
        System.out.println("setUpBeforeClass()");
    }

    @AfterClass
    public static void tearDownAfterClass() {
        System.out.println("tearDownAfterClass()");
    }

    @Before
    public void setUp() {
        System.out.println("    setUp()");
    }

    @After
    public void tearDown() {
        System.out.println("    tearDown()");
    }

    public SequenceTest() {
        System.out.println("  new SequenceTest()");
    }

    @Test
    public void testA() {
        System.out.println("    testA()");
    }

    @Test
    public void testB() {
        System.out.println("    testB()");
    }

    @Test
    public void testC() {
        System.out.println("    testC()");
    }

//方法执行顺序：
//setUpBeforeClass()
//  new SequenceTest()
//    setUp()
//    testA()
//    tearDown()
//  new SequenceTest()
//    setUp()
//    testB()
//    tearDown()
//  new SequenceTest()
//    setUp()
//    testC()
//    tearDown()
//tearDownAfterClass()
}
