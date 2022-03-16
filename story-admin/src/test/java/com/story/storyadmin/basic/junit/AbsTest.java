package com.story.storyadmin.basic.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * @author: lipan
 * @date: 2019-06-09
 * @description:
 *
 * 参数化测试：
 * 1。参数化测试必须由静态方法data()返回
 * 2。返回类型为Collection<Object[]>
 * 3。静态方法必须标记为@Parameters
 * 4。测试类必须标记为@RunWith(Parameterized.class)
 * 5。构造方法参数必须和测试参数对应
 */
@RunWith(Parameterized.class)
public class AbsTest {

    //@Parameters
    @Parameterized.Parameters
    public static Collection<?> data() {
        return Arrays.asList(new Object[][]{  //两个参数，所以是二维数组
                {0, 0}, {1, 1}, {-1, -1}  //这里是传入的测试数据，三组都会被用于测试，还可以根据的要求添加测试数据
        });
    }

    int input;  //输入值
    int expected;//输出值 期望值

    public AbsTest(int input, int expected) {
        this.input = input;
        this.expected = expected;
    }



    //测试方法
    @Test
    public void testAbs() {
        int r = Math.abs(this.input);
        assertEquals(this.expected, r);
    }

}
