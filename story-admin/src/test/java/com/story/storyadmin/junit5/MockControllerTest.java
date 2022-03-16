package com.story.storyadmin.junit5;

import com.alibaba.fastjson.JSONObject;
import javafx.application.Application;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
/**此处可以是静态引用，也可以不用静态引用，这样就需要在使用方法的时候加上对应的类*/
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author: 59688
 * @date: 2021/8/5
 * @description:
 */
@RunWith(SpringRunner.class)
//SpringBootTest 是springboot 用于测试的注解，可指定启动类或者测试环境等，这里直接默认。
//因为是mock测试，在实际开发过程中，可指定其测试启动时为随机端口，避免了不必要的端口冲突。
//当WebEnvironment.RANDOM_PORT或者WebEnvironment.DEFINED_PORT时，都会提供一个真实的web环境，只不过前者是随机端口，而后者是默认端口
@SpringBootTest(classes = Application.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//测试单一接口时 ，也可利用注解@WebMvcTest 进行单一测试
//@WebMvcTest(MockTestController.class)
public class MockControllerTest {

    //使用 WebMvcTest 时使用
    //@autowired mockMvc 是可自动注入的。
    //当直接使用SpringBootTest 会提示 注入失败  这里直接示例利用 MockMvcBuilders工具创建
    //@Autowired
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext wc;

    @Before
    public void beforeSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wc).build();
    }

    @Test
    public void testDemo() throws Exception {
        String msg = "this is a mock test";
        MvcResult result = this.mockMvc.perform(get("/mock")
                .param("msg", msg))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        //断言 是否和预期相等
        Assert.assertEquals(msg, result.getResponse().getContentAsString());

    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getHello2(){
        MultiValueMap map= new LinkedMultiValueMap();
        map.add("id","1");
        String response = restTemplate.postForObject("/api/hello/say",map,String.class);
        Assertions.assertThat(response).contains("hello");

        /**
         * 2、mock：默认提供一个模拟的web环境，不会启动内嵌的服务器，如果想启动内嵌web服务，可以使用@SpringBootTest的webEnviroment特性：
         * 指定了SpringBootTest的webEnvironment 属性的同时，会为我们自动装配一个TestRestTemplate类型的bean来辅助我们发送请求
         */
    }


    @Test
    public void signOutCurrentClass() throws Exception {
        this.mockMvc.perform(post("/user/selectByIdAndName") // //调用接口
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("userId", "11")
                .param("userName", "henry")//传入添加的用户参数

                .accept(MediaType.APPLICATION_JSON))  //接收的类型
                .andExpect(status().isOk())   //判断接收到的状态是否是200
                .andDo(print())  //打印内容
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(Matchers.containsString("OK"))) //匹配返回值中的内容
                .andExpect(jsonPath("$.errcode", is(0)));////使用jsonPath解析返回值，判断具体的内容

    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void testInfo() throws Exception {
        RequestBuilder request = post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("userId", "11").param("userName", "henry");
        MvcResult mvcResult = this.mockMvc.perform(request).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println("返回结果：" + status);
        System.out.println(content);

    }

    /**
     * 在post的接口中接收的参数为对象的话，可以进行（Json对象或者实体类的）封装
     * @throws Exception
     */
    @Test
    public void testInfo2() throws Exception {
    JSONObject param = new JSONObject() ;
    param.put("userId", "11");
    param.put("userName", "henry");
    String jsonstr = param.toString() ;
    System.out.println("================================请求入参："+jsonstr);

    RequestBuilder request = MockMvcRequestBuilders.post("/classmanage/signOutCurrentClass")
          .contentType(MediaType.APPLICATION_JSON)
          .content(jsonstr).accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = this.mockMvc.perform(request).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println("返回结果：" + status);
        System.out.println(content);

    }



}