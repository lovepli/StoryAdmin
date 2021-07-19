package com.story.storyadmin.junit5;

import com.story.storyadmin.StoryAdminApplicationTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: 59688
 * @date: 2021/7/14
 * @description: 使用JUnit5编写单元测试 https://blog.csdn.net/swift0824/article/details/83928694
 *
 * JUnit 5跟以前的JUnit版本不一样，它由几大不同的模块组成，这些模块分别来自三个不同的子项目。
 * JUnit 5 = JUnit Platform + JUnit Jupiter + JUnit Vintage
 * 主要注解：
 *
 * @BeforeAll 类似于JUnit 4的@BeforeAll,表示使用了该注解的方法应该在当前类中所有使用了@Test、@RepeatedTest、@ParameterizedTest或者@TestFactory注解的方法之前执行,必须为static
 * @BeforeEach 类似于JUnit 4的@Before,表示使用了该注解的方法应该在当前类中每一个使用了@Test、@RepeatedTest、@ParameterizedTest或者@TestFactory注解的方法之前执行
 * @Test 表示该方法是一个测试方法
 * @DisplayName 为测试类或测试方法声明一个自定义的显示名称
 * @AfterEach 类似于JUnit 4的@After,表示使用了该注解的方法应该在当前类中每一个使用了@Test、@RepeatedTest、@ParameterizedTest或者@TestFactory注解的方法之后执行
 * @AfterAll 类似于JUnit 4的@AfterClass,表示使用了该注解的方法应该在当前类中所有使用了@Test、@RepeatedTest、@ParameterizedTest或者@TestFactory注解的方法之后执行,必须为static
 * @Disable 用于禁用一个测试类或测试方法, 类似于JUnit 4的@Ignore
 * @ExtendWith 用于注册自定义扩展
 *
 * 常用注解#
 * @BeforeEach： 在每个单元测试方法执行前都执行一遍
 * @BeforeAll： 在每个单元测试方法执行前执行一遍（只执行一次）
 * @DisplayName("商品入库测试")： 用于指定单元测试的名称
 * @Disabled： 当前单元测试置为无效，即单元测试时跳过该测试
 * @RepeatedTest(n)： 重复性测试，即执行n次
 * @ParameterizedTest： 参数化测试，
 * @ValueSource(ints = {1, 2, 3})：参数化测试提供数据
 */
@DisplayName("Junit5测试") // https://www.cnblogs.com/haixiang/p/13812363.html
public class Junit5Test { //extends StoryAdminApplicationTests


    /**
     * 断言#
     * JUnit Jupiter提供了强大的断言方法用以验证结果，在使用时需要借助java8的新特性lambda表达式，均是来自org.junit.jupiter.api.Assertions包的static方法。
     * assertTrue与assertFalse用来判断条件是否为true或false
     */
    @Test
    @DisplayName("测试断言equals")
    void testEquals() {
        assertTrue(3 < 4);
    }

    /**
     * assertNull与assertNotNull用来判断条件是否为·null
     */
    @Test
    @DisplayName("测试断言NotNull")
    void testNotNull() {
        assertNotNull(new Object());
    }

    /**
     * ssertThrows用来判断执行抛出的异常是否符合预期，并可以使用异常类型接收返回值进行其他操作
     */
    @Test
    @DisplayName("测试断言抛异常")
    void testThrows() {
        ArithmeticException arithExcep = assertThrows(ArithmeticException.class, () -> {
            int m = 5/0;
        });
        assertEquals("/ by zero", arithExcep.getMessage());
    }

    /**
     * assertTimeout用来判断执行过程是否超时
     */
    //@Test
    //@DisplayName("测试断言超时")
    //void testTimeOut() {
    //    String actualResult = assertTimeout(ofSeconds(2), () -> {
    //        Thread.sleep(1000);
    //        return "a result";
    //    });
    //    System.out.println(actualResult);
    //}

    /**
     * assertAll是组合断言，当它内部所有断言正确执行完才算通过
     */
    @Test
    @DisplayName("测试组合断言")
    void testAll() {
        assertAll("测试item商品下单",
                () -> {
                    //模拟用户余额扣减
                    assertTrue(1 < 2, "余额不足");
                },
                () -> {
                    //模拟item数据库扣减库存
                    assertTrue(3 < 4);
                },
                () -> {
                    //模拟交易流水落库
                    assertNotNull(new Object());
                }
        );
    }

    /**
     * 重复性测试#
     * 在许多场景中我们需要对同一个接口方法进行重复测试，例如对幂等性接口的测试。
     * JUnit Jupiter通过使用@RepeatedTest(n)指定需要重复的次数
     */
    @RepeatedTest(3)
    @DisplayName("重复测试")
    void repeatedTest() {
        System.out.println("调用");
    }

    /**
     * 参数化测试#
     * 参数化测试可以按照多个参数分别运行多次单元测试这里有点类似于重复性测试，只不过每次运行传入的参数不用。
     * 需要使用到@ParameterizedTest，同时也需要@ValueSource提供一组数据，它支持八种基本类型以及String和自定义对象类型，
     * 使用极其方便。
     * @param a
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @DisplayName("参数化测试")
    void paramTest(int a) {
        assertTrue(a > 0 && a < 4);
    }

}
