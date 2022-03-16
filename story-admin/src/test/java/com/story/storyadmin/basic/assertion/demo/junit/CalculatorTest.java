package com.story.storyadmin.basic.assertion.demo.junit;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 使用Assert断言:
 * 断言相等：assertEquals(100,x)
 * 断言数组相等：assertArrayEquals({1,2,3},x)
 * 浮点数断言相等：assertEquals(3.1412,0.0001)
 * 断言为null: assertNull(x)
 * 断言为true/false: assertTrue(x>0)   assertFalse(x<0)
 * a 其他：ssertNotEquals  assertNotNull
 *
 */

public class CalculatorTest {

    Calculator calc;


    @Before
    public void setUp() {
        calc = new Calculator();   //初始化的资源
    }



//    @Test  //可以一次测试多个
//    public void calculate() {
//        assertEquals(3,new Calculator().calculate("1+2"));
//        assertEquals(6,new Calculator().calculate("1+2+3"));
//        assertEquals(35,new Calculator().calculate("12+23"));
//    }


    @Test
    public void testCalcAdd1Numbers() {
        // Calculator calc = new Calculator();
        int r = calc.calculate("  1 + 2 ");//不规范的输入
        assertEquals(3,r);
    }

    @Test
    public void testCalcAdd2Numbers() {
       // Calculator calc = new Calculator();
        int r = calc.calculate("1+2");
        assertEquals(3,r);
    }

    @Test
    public void testCalcAdd3Numbers() {
       // Calculator calc = new Calculator();
        int r = calc.calculate("1+2+5");
        assertEquals(8,r);
    }



    /**
     * //异常测试:
     * 1。测试异常可以使用@Test(expected = Exception.class)
     * 2。对可能发生的每种类型的异常进行测试
     */

//    @Test
//    public void testCalcWithEmptyString() {
//        calc.calculate("");    //结果报错：java.lang.NumberFormatException: For input string: ""
//    }

    //@Test  --> @Test(expected = NumberFormatException.class) 测试通过
    @Test(expected = NumberFormatException.class)
    public void testCalcWithEmptyString() {
        calc.calculate("");
    }

    @Test(expected = NullPointerException.class)
    public void testCalcWriteNull() {
        calc.calculate(null);  //java.lang.NullPointerException
    }
}