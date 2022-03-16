package com.story.storyadmin.junit5;

/**
 * @author: 59688
 * @date: 2021/8/5
 * @description:
 */

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * mock 基类
 * @author oKong
 * 鉴于每次编写控制层测试类时，都需要创建MockMvc对象，故可创建一个基类，这样省得每次都写。
 * 这样编写mock测试类时，还需要继承此基类即可。
 */
public abstract class BaseMockTest {

    @Autowired
    private WebApplicationContext wc;

    protected MockMvc mockMvc;

    @Before
    public void beforeSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wc).build();
    }

    /**
     * Junit常用注解说明
     * @Test 加在待测试的方法前面
     * @Before 带上@Test的方法执行前会执行该方法
     * @After 带上@Test的方法执行完毕后会执行该方法
     * @BeforeClass 加上这个注解，则该方法会第一个执行（在所有方法中），且方法要加上关键词static，是一个static方法
     * @AfterClass 加上这个注解，则该方法最后一个执行（在所有方法中）,同样，方法要加上关键词static，是一个static方法
     */

}
