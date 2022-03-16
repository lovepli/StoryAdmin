package com.story.storyadmin.designPatterns.test;

import com.story.storyadmin.designPatterns.payService.PayService;
import com.story.storyadmin.designPatterns.payService2.PayService2;
import com.story.storyadmin.designPatterns.payService3.PayService3;
import com.story.storyadmin.designPatterns.payService4.PayService4;
import com.story.storyadmin.designPatterns.payService5.PayService5;
import com.story.storyadmin.designPatterns.payService6.PayHandlerChain;
import com.story.storyadmin.designPatterns.payService7.PayService7;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: lipan
 * @date: 2021/8/18
 * @description: 策略模式使用的就是面向对象的继承和多态机制，从而实现同一行为在不同场景下不同实现。
 */
@ExtendWith(SpringExtension.class) //导入spring测试框架[2]
@SpringBootTest  //提供spring依赖注入
@Transactional  //事务管理，默认回滚,如果配置了多数据源记得指定事务管理器
@DisplayName("利用设计模式如何去掉多个if-else的测试")
public class PayTest {

    @Autowired
    private PayService payService;

    @Autowired
    private PayService2 payService2;

    @Autowired
    private PayService3 payService3;

    @Autowired
    private PayService4 payService4;

    @Autowired
    private PayService5 payService5;

    @Autowired
    private PayService7 payService7;

    @Autowired
    private PayHandlerChain payHandlerChain;


    private static String code;

    @BeforeAll //在所有测试方法前执行，只执行一次
    public static void setUp() {
        code="alia";
    }

    @Test
    @DisplayName("单元测试")
    public void test1(){
        payService.toPay(code);
    }

    @Test
    @DisplayName("单元测试2")
    public void test2(){
        payService2.toPay(code);
    }

    @Test
    @DisplayName("单元测试3")
    public void test3(){
        payService3.toPay(code);
    }

    @Test
    @DisplayName("单元测试4")
    public void test4(){
        payService4.toPay(code);
    }

    @Test
    @DisplayName("单元测试5")
    public void test5(){
        String code2 ="weixinPay5";
        payService5.toPay(code2);
    }

    @Test
    @DisplayName("单元测试6")
    public void test6(){
        payHandlerChain.handlePay(code);
    }

    @Test
    @DisplayName("单元测试7")
    public void test7(){
        payService7.toPay(code);
    }
}
