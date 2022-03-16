package com.story.storyadmin.enumtest.enumeratorTest;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.story.storyadmin.enumerator.demo3.GenderEnum;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
import javax.annotation.Resource;

/**
 * @author: 59688
 * @date: 2021/7/21
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EnumeratorTest extends TestCase {

    private static final Logger logger = LoggerFactory.getLogger(EnumeratorTest.class);

    @Resource
    private ObjectMapper objectMapper;

    /**
     *
     */
    @Test
    public void enumTest() {
        try {
            String s = objectMapper.writeValueAsString(GenderEnum.MALE);
            // 输出字符串 MALE
            System.out.println(s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 没开启全局设置，运行结果如下：
     * "MALE"
     * 开启全局设置，运行结果如下：
     * {"code":1,"description":"男"}
     */
}
